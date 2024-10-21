package com.ivantrykosh.app.test_task_for_boosteight.domain.use_case.heart_rate

import com.ivantrykosh.app.test_task_for_boosteight.domain.model.HeartRate
import com.ivantrykosh.app.test_task_for_boosteight.domain.repository.HeartRateRepository
import com.ivantrykosh.app.test_task_for_boosteight.utils.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Use case for inserting heart rate
 */
class InsertHeartRateUseCase @Inject constructor(
    private val heartRateRepository: HeartRateRepository
) {
    /**
     * Inserts heart rate
     *
     * @param heartRate heart rate to insert
     * @return flow that emits resource state of operation
     */
    operator fun invoke(heartRate: HeartRate) = flow<Resource<Unit>> {
        try {
            emit(Resource.Loading())
            heartRateRepository.insertHeartRate(heartRate)
            emit(Resource.Success())
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error has occurred"))
        }
    }
}