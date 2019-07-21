package com.maruani.esgi.summaryapplication.module.home

import com.maruani.esgi.summaryapplication.module.common.BasePresenter

class HomePresenter : BasePresenter<HomeView>() {
    fun onRecyclerButtonClick() {
        navigator.toSimpleList()
    }

    fun onCustomViewClick() {
        navigator.toCustomView()
    }

    fun onArchComponentClick() {
        navigator.toArchComponent()
    }

    fun onValueAnimatorClick() {
        navigator.toValueAnimator()
    }

    fun onConstraintSetClick() {
        navigator.toConstraintSet()
    }

    fun onMotionLayoutClick() {
        navigator.toMotionLayout()
    }

    fun onLottieClick() {
        navigator.toLottie()
    }

    fun onSensorClick() {
        navigator.toSensor()
    }

    fun onCanvasClick() {
        navigator.toCanvas()
    }

    fun onSurfaceClick() {
        navigator.toSurface()
    }
}