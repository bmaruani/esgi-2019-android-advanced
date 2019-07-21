package com.maruani.esgi.summaryapplication.module.canvas

import android.view.MotionEvent
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.maruani.esgi.summaryapplication.R


class CanvasView(context: Context, attributeSet: AttributeSet?) : View(context) {

    private val paint: Paint
    private val path: Path
    private val drawColor: Int
    private val backgroundColor: Int
    private var extraCanvas: Canvas? = null
    private var extraBitmap: Bitmap? = null
    private var frame: Rect? = null

    // Variables for the latest x,y values,
    // which are the starting point for the next path.
    private var lastX: Float = 0.toFloat()
    private var lastY: Float = 0.toFloat()

    internal constructor(context: Context) : this(context, null) {}

    init {

        backgroundColor = ResourcesCompat.getColor(
            resources,
            R.color.opaque_orange, null
        )
        drawColor = ResourcesCompat.getColor(
            resources,
            R.color.opaque_yellow, null
        )

        // Holds the path we are currently drawing.
        path = Path()
        // Set up the paint with which to draw.
        paint = Paint().apply {
            color = drawColor
            // Smoothes out edges of what is drawn without affecting shape.
            isAntiAlias = true
            // Dithering affects how colors with higher-precision
            // than the device are down-sampled.
            isDither = true
            style = Paint.Style.STROKE // default: FILL
            strokeJoin = Paint.Join.ROUND // default: MITER
            strokeCap = Paint.Cap.ROUND // default: BUTT
            strokeWidth = 12f // default: Hairline-width (really thin)
        }

    }

    /**
     * Note: Called whenever the view changes size.
     * Since the view starts out with no size, this is also called after
     * the view has been inflated and has a valid size.
     */
    override fun onSizeChanged(
        width: Int, height: Int,
        oldWidth: Int, oldHeight: Int
    ) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        // Create bitmap, create canvas with bitmap, fill canvas with color.
        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas!!.drawColor(backgroundColor)

        // Calculate the rect a frame around the picture.
        val inset = 40
        frame = Rect(inset, inset, width - inset, height - inset)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw the bitmap that has the saved path.
        canvas.drawBitmap(extraBitmap, 0f, 0f, null)

        // Draw a frame around the picture.
        canvas.drawRect(frame, paint)
    }

    // The following methods factor out what happens for different touch events,
    // as determined by the onTouchEvent() switch statement.
    // This keeps the switch statement
    // concise and and easier to change what happens for each event.

    private fun touchStart(x: Float, y: Float) {
        path.moveTo(x, y)
        lastX = x
        lastY = y
    }

    private fun touchMove(x: Float, y: Float) {
        val dx = Math.abs(x - lastX)
        val dy = Math.abs(y - lastY)
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            // QuadTo() adds a quadratic bezier from the last point,
            // approaching control point (x1,y1), and ending at (x2,y2).
            path.quadTo(lastX, lastY, (x + lastX) / 2, (y + lastY) / 2)
            lastX = x
            lastY = y
            // Draw the path in the extra bitmap to save it.
            extraCanvas!!.drawPath(path, paint)
        }
    }

    private fun touchUp() {
        // Reset the path so it doesn't get drawn again.
        path.reset()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        // Invalidate() is inside the case statements because there are many
        // other types of motion events passed into this listener,
        // and we don't want to invalidate the view for those.
        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart(x, y)
            MotionEvent.ACTION_MOVE -> {
                touchMove(x, y)
                invalidate()
            }
            MotionEvent.ACTION_UP -> touchUp()
        }// No need to invalidate because we are not drawing anything.
        // do nothing
        return true
    }

    companion object {
        // Don't draw every single pixel.
        // If the finger has has moved less than this distance, don't draw.
        private const val TOUCH_TOLERANCE = 4f
    }
}