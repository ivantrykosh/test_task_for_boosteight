package com.ivantrykosh.app.test_task_for_boosteight.domain.repository

import com.ivantrykosh.app.test_task_for_boosteight.domain.model.HeartRate

/**
 * HeartRate repository with corresponding methods
 */
interface HeartRateRepository {

    /**
     * Insert heart rate
     * @param heartRate heart rate to insert
     */
    suspend fun insertHeartRate(heartRate: HeartRate)

    /**
     * Delete all heart rates
     */
    suspend fun deleteAllHeartRates()

    /**
     * Get all heart rates
     * @return list of all heart rates
     */
    suspend fun getAllHeartRates(): List<HeartRate>
}