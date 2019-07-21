package com.maruani.esgi.summaryapplication.module.animation.constraintSet

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import com.maruani.esgi.summaryapplication.R

class ConstraintSetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_set)

        val rootLayout: ConstraintLayout = findViewById(R.id.constraint_set_root_layout)

        val constraintSetInit = ConstraintSet()
        constraintSetInit.clone(rootLayout)

        val constraintSetDest = ConstraintSet()
        constraintSetDest.clone(this, R.layout.activity_constraint_set_dest)

        var isDestinationState = false

        findViewById<View>(R.id.imageView).setOnClickListener {
            TransitionManager.beginDelayedTransition(rootLayout)

            if (isDestinationState) {
                constraintSetInit.applyTo(rootLayout)
            } else {
                constraintSetDest.applyTo(rootLayout)
            }
            isDestinationState = !isDestinationState
        }
    }
}
