package com.maruani.esgi.summaryapplication.module.common.navigator

import androidx.annotation.AnimRes
import com.maruani.esgi.summaryapplication.R

/**
 * Created on 2019-07-17 by cyrilicard
 *
 */
enum class NavigationAnimation(@AnimRes val enter: Int, @AnimRes val exit: Int) {
    ENTER_FROM_RIGHT(R.anim.slide_in_from_right, R.anim.slide_out_to_left),
    EXIT_TO_LEFT(R.anim.slide_in_from_left, R.anim.slide_out_to_right),
    FADE(R.anim.fade_in, R.anim.fade_out);
}