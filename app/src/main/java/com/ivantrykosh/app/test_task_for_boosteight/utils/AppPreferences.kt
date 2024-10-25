package com.ivantrykosh.app.test_task_for_boosteight.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit

/**
 * Object for storing single values on the device
 */
object AppPreferences {
    private const val APP_PREFERENCES_NAME = "heart_rate_app_prefs"
    private var sharedPreferences: SharedPreferences? = null

    fun setup(context: Context) {
        sharedPreferences = context.getSharedPreferences(APP_PREFERENCES_NAME, MODE_PRIVATE)

        // Setup default values
        isFirstSession = isFirstSession ?: true
        isFirstMeasurement = isFirstMeasurement ?: true
    }

    /**
     * Value for checking, if this is first user session
     */
    var isFirstSession: Boolean?
        get() = Key.IS_FIRST_SESSION.getBoolean()
        set(value) = Key.IS_FIRST_SESSION.setBoolean(value)!!

    /**
     * Value for checking, if this is first heart rate measurement
     */
    var isFirstMeasurement: Boolean?
        get() = Key.IS_FIRST_MEASUREMENT.getBoolean()
        set(value) = Key.IS_FIRST_MEASUREMENT.setBoolean(value)!!

    private enum class Key {
        IS_FIRST_SESSION, IS_FIRST_MEASUREMENT;

        /**
         * Get boolean from preferences by key
         */
        fun getBoolean(): Boolean? = if (sharedPreferences!!.contains(name)) sharedPreferences!!.getBoolean(name, true) else null

        /**
         * Set boolean for preference by key and value
         * @param value value to set
         */
        fun setBoolean(value: Boolean?) = value?.let { sharedPreferences!!.edit { putBoolean(name, value) } }
    }
}