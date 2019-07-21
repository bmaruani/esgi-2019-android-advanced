package com.maruani.esgi.summaryapplication.module.customview

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.maruani.esgi.summaryapplication.R

class CustomViewActivity : AppCompatActivity() {

    lateinit var emotionalFaceView: EmotionalFaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)

        emotionalFaceView = findViewById(R.id.custom_view_emotionalFaceView)

        findViewById<View>(R.id.custom_view_happy_btn).setOnClickListener {
            emotionalFaceView.happinessState = EmotionalFaceView.HAPPY
        }

        findViewById<View>(R.id.custom_view_sad_btn).setOnClickListener {
            emotionalFaceView.happinessState = EmotionalFaceView.SAD
        }

        val customComponent = CustomComponent(this)
        customComponent.apply {
            textColor = Color.CYAN
            setText(R.string.app_name)
            imageResource = android.R.drawable.btn_star_big_on
        }


        val containerView: LinearLayout = findViewById(R.id.custom_view_container)
        containerView.addView(customComponent)

    }
}
