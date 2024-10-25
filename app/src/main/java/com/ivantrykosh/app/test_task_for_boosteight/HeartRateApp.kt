package com.ivantrykosh.app.test_task_for_boosteight

import android.app.Application
import com.ivantrykosh.app.test_task_for_boosteight.utils.AppPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HeartRateApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppPreferences.setup(this)
    }
}