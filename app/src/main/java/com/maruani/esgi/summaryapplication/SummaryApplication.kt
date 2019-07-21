package com.maruani.esgi.summaryapplication

import android.app.Application
import com.facebook.stetho.Stetho

class SummaryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}