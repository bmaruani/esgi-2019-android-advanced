package com.maruani.esgi.summaryapplication.module.common.navigator

import android.app.Activity
import com.maruani.esgi.summaryapplication.module.animation.constraintSet.ConstraintSetActivity
import com.maruani.esgi.summaryapplication.module.animation.lottie.LottieActivity
import com.maruani.esgi.summaryapplication.module.animation.motionLayout.MotionLayoutActivity
import com.maruani.esgi.summaryapplication.module.animation.valueAnimator.ValueAnimatorActivity
import com.maruani.esgi.summaryapplication.module.architectureComponent.RootActivity
import com.maruani.esgi.summaryapplication.module.canvas.CanvasActivity
import com.maruani.esgi.summaryapplication.module.customview.CustomViewActivity
import com.maruani.esgi.summaryapplication.module.home.HomeActivity
import com.maruani.esgi.summaryapplication.module.photoDetail.PhotoDetailActivity
import com.maruani.esgi.summaryapplication.module.sensor.SensorActivity
import com.maruani.esgi.summaryapplication.module.simpleList.SimpleListActivity
import com.maruani.esgi.summaryapplication.module.surface.SurfaceActivity

enum class Route constructor(val activityClass: Class<out Activity>, vararg val parametersKeys: String) {

    HOME(HomeActivity::class.java),
    SIMPLE_LIST(SimpleListActivity::class.java),
    PHOTO_DETAIL(PhotoDetailActivity::class.java, PhotoDetailActivity.PHOTO_ID),
    CUSTOM_VIEW(CustomViewActivity::class.java),
    ARCH_COMPONENT(RootActivity::class.java),
    VALUE_ANIMATOR(ValueAnimatorActivity::class.java),
    CONSTRAINT_SET(ConstraintSetActivity::class.java),
    MOTION_LAYOUT(MotionLayoutActivity::class.java),
    LOTTIE(LottieActivity::class.java),
    SENSOR(SensorActivity::class.java),
    CANVAS(CanvasActivity::class.java),
    SURFACE(SurfaceActivity::class.java)

}