package com.ivantrykosh.app.test_task_for_boosteight.domain.use_case.heart_rate

import com.ivantrykosh.app.test_task_for_boosteight.domain.repository.HeartRateRepository
import com.ivantrykosh.app.test_task_for_boosteight.utils.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Use case for deleting all heart rates
 */
class DeleteAllHeartRatesUseCase @Inject constructor(
    private val heartRateRepository: HeartRateRepository
) {
    /**
     * Deletes all heart rates
     *
     * @return flow that emits resource state of operation
     */
    operator fun invoke() = flow<Resource<Unit>> {
        try {
            emit(Resource.Loading())
            heartRateRepository.deleteAllHeartRates()
            emit(Resource.Success())
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error has occurred"))
        }
    }
}