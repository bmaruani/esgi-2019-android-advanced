package com.maruani.esgi.summaryapplication.module.animation.valueAnimator

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.maruani.esgi.summaryapplication.R

class ValueAnimatorActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_value_animator)

        val textView: TextView = findViewById(R.id.value_animator_tv)
        val rootView: View = findViewById(android.R.id.content)

        val valueAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
            interpolator = AccelerateDecelerateInterpolator()
            duration = 2000
        }

        valueAnimator.addUpdateListener { animator ->
            val progress: Float = animator.animatedValue as Float
            val maxY = rootView.height - textView.height - 20

            textView.scaleX = progress + 1
            textView.scaleY = progress + 1
            textView.translationY = progress * maxY
        }

        textView.setOnClickListener { valueAnimator.start() }
    }
}
