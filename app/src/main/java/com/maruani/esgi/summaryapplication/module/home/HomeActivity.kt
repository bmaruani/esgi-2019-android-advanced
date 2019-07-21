package com.maruani.esgi.summaryapplication.module.home

import android.os.Bundle
import android.widget.Button
import com.maruani.esgi.summaryapplication.R
import com.maruani.esgi.summaryapplication.module.common.BaseActivity

class HomeActivity : BaseActivity<HomePresenter, HomeView>(R.layout.activity_main, HomePresenter()), HomeView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<Button>(R.id.activity_home_recycler_btn).setOnClickListener { presenter.onRecyclerButtonClick() }
        findViewById<Button>(R.id.activity_home_customview_btn).setOnClickListener { presenter.onCustomViewClick() }
        findViewById<Button>(R.id.activity_home_arch_component_btn).setOnClickListener { presenter.onArchComponentClick() }
        findViewById<Button>(R.id.activity_home_value_animator_btn).setOnClickListener { presenter.onValueAnimatorClick() }
        findViewById<Button>(R.id.activity_home_constraintset_btn).setOnClickListener { presenter.onConstraintSetClick() }
        findViewById<Button>(R.id.activity_home_motion_layout_btn).setOnClickListener { presenter.onMotionLayoutClick() }
        findViewById<Button>(R.id.activity_home_lottie_btn).setOnClickListener { presenter.onLottieClick() }
        findViewById<Button>(R.id.activity_home_sensor_btn).setOnClickListener { presenter.onSensorClick() }
        findViewById<Button>(R.id.activity_home_canvas_btn).setOnClickListener { presenter.onCanvasClick() }
        findViewById<Button>(R.id.activity_home_surface_btn).setOnClickListener { presenter.onSurfaceClick() }
    }
}
