package com.ivantrykosh.app.test_task_for_boosteight.domain.use_case.heart_rate

import com.ivantrykosh.app.test_task_for_boosteight.data.repository.HeartRateRepositoryImpl
import com.ivantrykosh.app.test_task_for_boosteight.domain.model.HeartRate
import com.ivantrykosh.app.test_task_for_boosteight.utils.Resource
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.cancel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class InsertHeartRateTypeUseCaseTest {

    private lateinit var heartRateRepositoryImpl: HeartRateRepositoryImpl
    private lateinit var insertHeartRateUseCase: InsertHeartRateUseCase

    @Before
    fun setup() {
        heartRateRepositoryImpl = mock()
        insertHeartRateUseCase = InsertHeartRateUseCase(heartRateRepositoryImpl)
    }

    @Test
    fun insertHeartRateSuccess() = runBlocking {
        val heartRate = HeartRate(heartRate = 80, dateTime = 177331841331)
        whenever(heartRateRepositoryImpl.insertHeartRate(heartRate)).doReturn(Unit)
        insertHeartRateUseCase(heartRate).collect { result ->
            when (result) {
                is Resource.Loading -> { }
                is Resource.Error -> { Assert.fail("Something went wrong") }
                is Resource.Success -> { }
            }
        }
        verify(heartRateRepositoryImpl).insertHeartRate(heartRate)
    }

    @Test(expected = CancellationException::class)
    fun insertHeartRateCheckFirstEmit() = runBlocking {
        val heartRate = HeartRate(heartRate = 80, dateTime = 177331841331)
        whenever(heartRateRepositoryImpl.insertHeartRate(heartRate)).doReturn(Unit)
        insertHeartRateUseCase(heartRate).collect { result ->
            when (result) {
                is Resource.Loading -> { this.cancel() }
                is Resource.Error -> { Assert.fail("Must be loading") }
                is Resource.Success -> { Assert.fail("Must be loading") }
            }
        }
    }
}