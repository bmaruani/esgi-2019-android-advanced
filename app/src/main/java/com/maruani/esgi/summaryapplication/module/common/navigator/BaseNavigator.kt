package com.maruani.esgi.summaryapplication.module.common.navigator

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.core.app.ActivityOptionsCompat

/**
 * Created by robincollet on 19/04/2018.
 */
abstract class BaseNavigator(private val activity: Activity) {

    private fun goToWithAnimation(
        route: Route,
        animation: NavigationAnimation,
        values: Array<String>,
        options: ActivityOptionsCompat?
    ): Boolean {
        val intent = Intent(activity, route.activityClass)
        val keys = route.parametersKeys

        if (keys.size != values.size) {
            Log.e(
                "BaseNavigator",
                "To launch this route, be sure to give the exact amount of parameter values. ${keys.size} keys and ${values.size} values"
            )
            return false
        }

        keys.indices.forEach { intent.apply { putExtra(keys[it], values[it]) } }
        activity.startActivity(intent, options?.toBundle())
        activity.overridePendingTransition(animation.enter, animation.exit)

        return true
    }

    fun goTo(
        route: Route,
        animation: NavigationAnimation = NavigationAnimation.FADE,
        values: Array<String> = emptyArray(),
        options: ActivityOptionsCompat? = null
    ): Boolean {
        return goToWithAnimation(route, animation, values, options)
    }

}