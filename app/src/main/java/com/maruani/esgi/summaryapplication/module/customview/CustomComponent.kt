package com.maruani.esgi.summaryapplication.module.customview

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.maruani.esgi.summaryapplication.R

class CustomComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {


    private val textView: TextView
    private val imageView: ImageView

    var text: CharSequence? = null
        set(value) = updateText(value)

    var textColor: Int? = null
        set(value) = updateTextColor(value)

    var imageResource: Int? = null
        set(value) = updateImageSrc(value)


    init {
        LayoutInflater.from(context).inflate(R.layout.component_custom, this, true)

        textView = findViewById(R.id.component_custom_tv)
        imageView = findViewById(R.id.component_custom_imv)

        attrs?.also { applyAttributes(it) }
    }

    private fun applyAttributes(attrs: AttributeSet) {
        // Obtain a typed array of attributes
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.CustomComponent,
            0, 0
        )

        val typedValue = TypedValue()
        typedArray.getValue(R.styleable.CustomComponent_text, typedValue)
        when (typedValue.type) {
            TypedValue.TYPE_REFERENCE -> setText(typedValue.resourceId)
            TypedValue.TYPE_STRING -> updateText(typedValue.string)
        }

        val color = typedArray.getColor(R.styleable.CustomComponent_textColor, DEFAULT_COLOR)
        val src = typedArray.getResourceId(R.styleable.CustomComponent_src, NO_RESOURCE_ID)

        // TypedArray objects are shared and must be recycled.
        typedArray.recycle()

        updateTextColor(color)
        updateImageSrc(src)
    }

    fun setText(resid: Int) {
        if (resid == NO_RESOURCE_ID) return

        try {
            updateText(context.resources.getText(resid))
        } catch (exception: Exception) {
            Log.w("CustomComponent", "Unable to find resource: $resid", exception);
        }
    }

    private fun updateText(charSequence: CharSequence?) {
        textView.text = charSequence
    }

    private fun updateTextColor(color: Int?) {
        color?.also { textView.setTextColor(it) }
    }

    private fun updateImageSrc(imageResource: Int?) {
        if (imageResource == null || imageResource == NO_RESOURCE_ID) {
            imageView.visibility = View.GONE
        } else {
            try {
                imageView.visibility = View.VISIBLE
                imageView.setImageResource(imageResource)
            } catch (exception: Exception) {
                Log.w("CustomComponent", "Unable to find resource: $imageResource", exception);
            }
        }
    }

    companion object {
        private const val NO_RESOURCE_ID = 0
        private const val DEFAULT_COLOR = Color.BLACK
    }
}