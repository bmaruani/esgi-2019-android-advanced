package com.maruani.esgi.androidadvanced.module.common

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.maruani.esgi.androidadvanced.module.common.navigator.Navigator

abstract class BasePresenter<V : BaseView> : LifecycleObserver {
    lateinit var view: V
    lateinit var navigator: Navigator

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {

    }
}