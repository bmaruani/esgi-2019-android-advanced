package com.maruani.esgi.summaryapplication.module.surface

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.maruani.esgi.summaryapplication.R
import kotlin.math.floor


class GameView @JvmOverloads constructor(private val mContext: Context, attrs: AttributeSet? = null) :
    SurfaceView(mContext, attrs), Runnable {

    private var mRunning: Boolean = false
    private var mGameThread: Thread? = null
    private val mPath: Path

    private var mFlashlightCone: FlashlightCone? = null

    private val mPaint: Paint
    private var mBitmap: Bitmap? = null
    private var mWinnerRect: RectF? = null
    private var mBitmapX: Int = 0
    private var mBitmapY: Int = 0
    private var mViewWidth: Int = 0
    private var mViewHeight: Int = 0
    private val mSurfaceHolder: SurfaceHolder

    init {
        mSurfaceHolder = holder
        mPaint = Paint()
        mPaint.color = Color.DKGRAY
        mPath = Path()
    }

    /**
     * We cannot get the correct dimensions of views in onCreate because
     * they have not been inflated yet. This method is called every time the
     * size of a view changes, including the first time after it has been
     * inflated.
     *
     * @param w Current width of view.
     * @param h Current height of view.
     * @param oldw Previous width of view.
     * @param oldh Previous height of view.
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mViewWidth = w
        mViewHeight = h

        mFlashlightCone = FlashlightCone(mViewWidth, mViewHeight)

        // Set font size proportional to view size.
        mPaint.textSize = (mViewHeight / 5f)

        mBitmap = BitmapFactory.decodeResource(
            mContext.resources, R.drawable.android
        )
        setUpBitmap()
    }

    /**
     * Runs in a separate thread.
     * All drawing happens here.
     */
    override fun run() {

        var canvas: Canvas

        while (mRunning) {
            // If we can obtain a valid drawing surface...
            if (mSurfaceHolder.surface.isValid) {

                // Helper variables for performance.
                val x = mFlashlightCone!!.x
                val y = mFlashlightCone!!.y
                val radius = mFlashlightCone!!.radius

                // Lock the canvas. Note that in a more complex app, with
                // more threads, you need to put this into a try/catch block
                // to make sure only one thread is drawing to the surface.
                // Starting with O, you can request a hardware surface with
                //    lockHardwareCanvas().
                // See https://developer.android.com/reference/android/view/
                //    SurfaceHolder.html#lockHardwareCanvas()
                canvas = mSurfaceHolder.lockCanvas()

                // Fill the canvas with white and draw the bitmap.
                canvas.save()
                canvas.drawColor(Color.WHITE)
                canvas.drawBitmap(mBitmap, mBitmapX / 1f, mBitmapY / 1f, mPaint)

                // Add clipping region and fill rest of the canvas with black.
                mPath.addCircle(x / 1f, y / 1f, radius / 1f, Path.Direction.CCW)

                // The method clipPath(path, Region.Op.DIFFERENCE) was
                // deprecated in API level 26. The recommended alternative
                // method is clipOutPath(Path), which is currently available
                // in API level 26 and higher.
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                    canvas.clipPath(mPath, Region.Op.DIFFERENCE)
                } else {
                    canvas.clipOutPath(mPath)
                }

                canvas.drawColor(Color.BLACK)

                // If the x, y coordinates of the user touch are within a
                //  bounding rectangle, display the winning message.
                if (x > mWinnerRect!!.left && x < mWinnerRect!!.right
                    && y > mWinnerRect!!.top && y < mWinnerRect!!.bottom
                ) {
                    canvas.drawColor(Color.WHITE)
                    canvas.drawBitmap(mBitmap, mBitmapX / 1f, mBitmapY / 1f, mPaint)
                    canvas.drawText(
                        "WIN!", mViewWidth / 3f, mViewHeight / 2f, mPaint
                    )
                }
                // Clear the path data structure.
                mPath.rewind()
                // Restore the previously saved (default) clip and matrix state.
                canvas.restore()
                // Release the lock on the canvas and show the surface's
                // contents on the screen.
                mSurfaceHolder.unlockCanvasAndPost(canvas)
            }
        }
    }

    /**
     * Updates the game data.
     * Sets new coordinates for the flashlight cone.
     *
     * @param newX New x position of touch event.
     * @param newY New y position of touch event.
     */
    private fun updateFrame(newX: Int, newY: Int) {
        mFlashlightCone!!.update(newX, newY)
    }

    /**
     * Calculates a randomized location for the bitmap
     * and the winning bounding rectangle.
     */
    private fun setUpBitmap() {
        mBitmapX = floor(Math.random() * (mViewWidth - mBitmap!!.width)).toInt()
        mBitmapY = floor(Math.random() * (mViewHeight - mBitmap!!.height)).toInt()
        mWinnerRect = RectF(
            mBitmapX.toFloat(), mBitmapY.toFloat(),
            (mBitmapX + mBitmap!!.width).toFloat(),
            (mBitmapY + mBitmap!!.height).toFloat()
        )
    }

    /**
     * Called by MainActivity.onPause() to stop the thread.
     */
    fun pause() {
        mRunning = false
        try {
            // Stop the thread == rejoin the main thread.
            mGameThread!!.join()
        } catch (e: InterruptedException) {
        }

    }

    /**
     * Called by MainActivity.onResume() to start a thread.
     */
    fun resume() {
        mRunning = true
        mGameThread = Thread(this)
        mGameThread!!.start()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        // Invalidate() is inside the case statements because there are
        // many other motion events, and we don't want to invalidate
        // the view for those.
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                setUpBitmap()
                // Set coordinates of flashlight cone.
                updateFrame(x.toInt(), y.toInt())
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                // Updated coordinates for flashlight cone.
                updateFrame(x.toInt(), y.toInt())
                invalidate()
            }
        }// Do nothing.
        return true
    }
}
