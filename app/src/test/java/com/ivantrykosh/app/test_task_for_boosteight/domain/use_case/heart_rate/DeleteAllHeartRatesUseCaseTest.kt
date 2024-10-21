package com.ivantrykosh.app.test_task_for_boosteight.domain.use_case.heart_rate

import com.ivantrykosh.app.test_task_for_boosteight.data.repository.HeartRateRepositoryImpl
import com.ivantrykosh.app.test_task_for_boosteight.utils.Resource
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.cancel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class DeleteAllHeartRatesUseCaseTest {

    private lateinit var heartRateRepositoryImpl: HeartRateRepositoryImpl
    private lateinit var deleteAllHeartRatesUseCase: DeleteAllHeartRatesUseCase

    @Before
    fun setup() {
        heartRateRepositoryImpl = mock()
        deleteAllHeartRatesUseCase = DeleteAllHeartRatesUseCase(heartRateRepositoryImpl)
    }

    @Test
    fun deleteAllHeartRatesSuccess() = runBlocking {
        whenever(heartRateRepositoryImpl.deleteAllHeartRates()).doReturn(Unit)
        deleteAllHeartRatesUseCase().collect { result ->
            when (result) {
                is Resource.Loading -> { }
                is Resource.Error -> { fail("Something went wrong") }
                is Resource.Success -> { }
            }
        }
        verify(heartRateRepositoryImpl).deleteAllHeartRates()
    }

    @Test(expected = CancellationException::class)
    fun deleteAllHeartRatesCheckFirstEmit() = runBlocking {
        whenever(heartRateRepositoryImpl.deleteAllHeartRates()).doReturn(Unit)
        deleteAllHeartRatesUseCase().collect { result ->
            when (result) {
                is Resource.Loading -> { this.cancel() }
                is Resource.Error -> { fail("Must be loading") }
                is Resource.Success -> { fail("Must be loading") }
            }
        }
    }
}