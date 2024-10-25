package com.ivantrykosh.app.test_task_for_boosteight.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ivantrykosh.app.test_task_for_boosteight.domain.model.HeartRate

/**
 * Data access object for HeartRate class
 */
@Dao
interface HeartRateDao {

    /**
     * Insert heart rate into db
     * @param heartRate heart rate to insert
     */
    @Insert
    suspend fun insertHeartRate(heartRate: HeartRate)

    /**
     * Delete all heart rates from db
     */
    @Query("DELETE FROM heart_rate")
    suspend fun deleteAllHeartRates()

    /**
     * Get all heart rates from db
     * @return list of all heart rates in db
     */
    @Query("SELECT * FROM heart_rate ORDER BY dateTime DESC")
    suspend fun getAllHeartRates(): List<HeartRate>
}