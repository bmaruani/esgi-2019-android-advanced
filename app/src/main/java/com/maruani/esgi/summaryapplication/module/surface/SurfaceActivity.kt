package com.maruani.esgi.summaryapplication.module.surface

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN



class SurfaceActivity : AppCompatActivity() {

    private lateinit var gameView: GameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Lock orientation into landscape.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Create a GameView and bind it to this activity.
        // You don't need a ViewGroup to fill the screen, because the system
        // has a FrameLayout to which this will be added.
        gameView = GameView(this);
        // Android 4.1 and higher simple way to request fullscreen.
        gameView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN;
        setContentView(gameView)
    }

    /**
     * Pauses game when activity is paused.
     */
    override fun onPause() {
        super.onPause()
        gameView.pause()
    }

    /**
     * Resumes game when activity is resumed.
     */
    override fun onResume() {
        super.onResume()
        gameView.resume()
    }
}
