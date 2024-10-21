package com.ivantrykosh.app.test_task_for_boosteight.data.repository

import com.ivantrykosh.app.test_task_for_boosteight.data.local.HeartRateDao
import com.ivantrykosh.app.test_task_for_boosteight.domain.model.HeartRate
import com.ivantrykosh.app.test_task_for_boosteight.domain.repository.HeartRateRepository
import javax.inject.Inject

/**
 * Implementation of HeartRateRepository
 */
class HeartRateRepositoryImpl @Inject constructor(
    private val heartRateDao: HeartRateDao
) : HeartRateRepository {
    override suspend fun insertHeartRate(heartRate: HeartRate) {
        heartRateDao.insertHeartRate(heartRate)
    }

    override suspend fun deleteAllHeartRates() {
        heartRateDao.deleteAllHeartRates()
    }

    override suspend fun getAllHeartRates(): List<HeartRate> {
        return heartRateDao.getAllHeartRates()
    }
}