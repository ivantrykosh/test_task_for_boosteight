package com.ivantrykosh.app.test_task_for_boosteight.domain.use_case.heart_rate

import com.ivantrykosh.app.test_task_for_boosteight.domain.repository.HeartRateRepository
import com.ivantrykosh.app.test_task_for_boosteight.utils.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Use case for getting all heart rates
 */
class GetAllHeartRatesUseCase @Inject constructor(
    private val heartRateRepository: HeartRateRepository
) {
    /**
     * Gets all heart rates
     *
     * @return flow that emits resource state of operation
     */
    operator fun invoke() = flow {
        try {
            emit(Resource.Loading())
            val heartRates = heartRateRepository.getAllHeartRates()
            emit(Resource.Success(heartRates))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error has occurred"))
        }
    }
}