package com.ivantrykosh.app.test_task_for_boosteight.domain.use_case.heart_rate

import com.ivantrykosh.app.test_task_for_boosteight.data.repository.HeartRateRepositoryImpl
import com.ivantrykosh.app.test_task_for_boosteight.domain.model.HeartRate
import com.ivantrykosh.app.test_task_for_boosteight.utils.Resource
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.cancel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetAllHeartRatesUseCaseTestType {

    private lateinit var heartRateRepositoryImpl: HeartRateRepositoryImpl
    private lateinit var getAllHeartRatesUseCase: GetAllHeartRatesUseCase

    @Before
    fun setup() {
        heartRateRepositoryImpl = mock()
        getAllHeartRatesUseCase = GetAllHeartRatesUseCase(heartRateRepositoryImpl)
    }

    @Test
    fun getAllHeartRatesSuccess() = runBlocking {
        val heartRates = listOf(HeartRate(heartRate = 80, dateTime = 1788452133), HeartRate(heartRate = 90, dateTime = 17587483134))
        whenever(heartRateRepositoryImpl.getAllHeartRates()).doReturn(heartRates)
        var retrievedHeartRates = listOf<HeartRate>()
        getAllHeartRatesUseCase().collect { result ->
            when (result) {
                is Resource.Loading -> { }
                is Resource.Error -> { Assert.fail("Something went wrong") }
                is Resource.Success -> { retrievedHeartRates = result.data!! }
            }
        }
        verify(heartRateRepositoryImpl).getAllHeartRates()
        assertEquals(heartRates.size, retrievedHeartRates.size)
        for (i in retrievedHeartRates.indices) {
            assertEquals(heartRates[i].heartRate, retrievedHeartRates[i].heartRate)
            assertEquals(heartRates[i].dateTime, retrievedHeartRates[i].dateTime)
        }
    }

    @Test(expected = CancellationException::class)
    fun getAllHeartRatesCheckFirstEmit() = runBlocking {
        val heartRates = listOf(HeartRate(heartRate = 80, dateTime = 1788452133), HeartRate(heartRate = 90, dateTime = 17587483134))
        whenever(heartRateRepositoryImpl.getAllHeartRates()).doReturn(heartRates)
        getAllHeartRatesUseCase().collect { result ->
            when (result) {
                is Resource.Loading -> { this.cancel() }
                is Resource.Error -> { Assert.fail("Must be loading") }
                is Resource.Success -> { Assert.fail("Must be loading") }
            }
        }
    }
}