package com.maruani.esgi.summaryapplication.module.common.navigator

import android.app.Activity
import androidx.core.app.ActivityOptionsCompat

class Navigator(activity: Activity) : BaseNavigator(activity) {

    fun toHome() = goTo(Route.HOME)
    fun toSimpleList() = goTo(Route.SIMPLE_LIST)
    fun toPhotoDetail(photoId: String, options: ActivityOptionsCompat) =
        goTo(Route.PHOTO_DETAIL, values = arrayOf(photoId), options = options)
    fun toCustomView() = goTo(Route.CUSTOM_VIEW, NavigationAnimation.ENTER_FROM_RIGHT)
    fun toArchComponent() = goTo(Route.ARCH_COMPONENT, NavigationAnimation.EXIT_TO_LEFT)
    fun toValueAnimator() = goTo(Route.VALUE_ANIMATOR)
    fun toConstraintSet() = goTo(Route.CONSTRAINT_SET)
    fun toMotionLayout() = goTo(Route.MOTION_LAYOUT)
    fun toLottie() = goTo(Route.LOTTIE)
    fun toSensor() = goTo(Route.SENSOR)
    fun toCanvas() = goTo(Route.CANVAS)
    fun toSurface() = goTo(Route.SURFACE)
}