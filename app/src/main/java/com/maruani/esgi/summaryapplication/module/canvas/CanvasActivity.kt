package com.maruani.esgi.summaryapplication.module.canvas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN

class CanvasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val canvasView = CanvasView(this)
        // Request the full available screen for layout.
        canvasView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(canvasView)
    }
}
