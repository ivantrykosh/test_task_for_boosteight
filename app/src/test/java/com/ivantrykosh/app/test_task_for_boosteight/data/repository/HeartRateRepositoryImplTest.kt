package com.ivantrykosh.app.test_task_for_boosteight.data.repository

import com.ivantrykosh.app.test_task_for_boosteight.data.local.HeartRateDao
import com.ivantrykosh.app.test_task_for_boosteight.domain.model.HeartRate
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class HeartRateRepositoryImplTest {

    private lateinit var mockHeartRateDao: HeartRateDao
    private lateinit var heartRateRepositoryImpl: HeartRateRepositoryImpl

    @Before
    fun setup() {
        mockHeartRateDao = mock()
        heartRateRepositoryImpl = HeartRateRepositoryImpl(mockHeartRateDao)
    }

    @Test
    fun insertHeartRate() = runTest {
        val heartRate = HeartRate(heartRate = 88, dateTime = 1774124411)
        whenever(mockHeartRateDao.insertHeartRate(heartRate)).doReturn(Unit)
        heartRateRepositoryImpl.insertHeartRate(heartRate)
        verify(mockHeartRateDao).insertHeartRate(heartRate)
    }

    @Test
    fun deleteAllHeartRates() = runTest {
        whenever(mockHeartRateDao.deleteAllHeartRates()).doReturn(Unit)
        heartRateRepositoryImpl.deleteAllHeartRates()
        verify(mockHeartRateDao).deleteAllHeartRates()
    }

    @Test
    fun getAllHeartRates() = runTest {
        val heartRates = listOf(HeartRate(heartRate = 80, dateTime = 1788452133), HeartRate(heartRate = 90, dateTime = 17587483134))
        whenever(mockHeartRateDao.getAllHeartRates()).doReturn(heartRates)
        val retrievedHeartRates = heartRateRepositoryImpl.getAllHeartRates()
        verify(mockHeartRateDao).getAllHeartRates()
        assertEquals(heartRates.size, retrievedHeartRates.size)
        for (i in retrievedHeartRates.indices) {
            assertEquals(heartRates[i].heartRate, retrievedHeartRates[i].heartRate)
            assertEquals(heartRates[i].dateTime, retrievedHeartRates[i].dateTime)
        }
    }
}