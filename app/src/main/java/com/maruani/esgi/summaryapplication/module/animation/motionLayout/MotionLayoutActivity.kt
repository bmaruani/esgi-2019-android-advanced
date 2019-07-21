package com.maruani.esgi.summaryapplication.module.animation.motionLayout

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.maruani.esgi.summaryapplication.R

class MotionLayoutActivity : AppCompatActivity() {

    private lateinit var topCard: View
    private lateinit var bottomCard: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_layout)

        topCard = findViewById(R.id.topCard)
        bottomCard = findViewById(R.id.bottomCard)

        val viewModel = ViewModelProviders
            .of(this)
            .get(SwipeRightViewModel::class.java)

        viewModel
            .modelStream
            .observe(this, Observer {
                bindCard(it)
            })

        val motionLayout: MotionLayout = findViewById(R.id.motionLayout)
        motionLayout.setTransitionListener(object : TransitionAdapter() {

            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                when (currentId) {
                    R.id.offScreenPass,
                    R.id.offScreenLike -> {
                        motionLayout.progress = 0f
                        motionLayout.setTransition(R.id.rest, R.id.like)
                        viewModel.swipe()
                    }
                }
            }

        })

        findViewById<View>(R.id.likeButton).setOnClickListener {
            motionLayout.transitionToState(R.id.like)
        }
        findViewById<View>(R.id.passButton).setOnClickListener {
            motionLayout.transitionToState(R.id.pass)
        }
    }

    private fun bindCard(model: SwipeRightModel) {
        topCard.setBackgroundColor(model.top.backgroundColor)
        bottomCard.setBackgroundColor(model.bottom.backgroundColor)
    }
}
