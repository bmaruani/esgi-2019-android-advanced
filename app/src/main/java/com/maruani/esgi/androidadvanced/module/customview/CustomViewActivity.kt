package com.maruani.esgi.androidadvanced.module.customview

import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.maruani.esgi.androidadvanced.R

class CustomViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)

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
