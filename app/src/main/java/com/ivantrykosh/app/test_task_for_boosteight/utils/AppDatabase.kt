package com.ivantrykosh.app.test_task_for_boosteight.utils

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ivantrykosh.app.test_task_for_boosteight.data.local.HeartRateDao
import com.ivantrykosh.app.test_task_for_boosteight.domain.model.HeartRate

/**
 * Local database of app
 */
@Database(entities = [HeartRate::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Abstract method that returns HeartRateDao
     */
    abstract fun heartRateDao(): HeartRateDao
}