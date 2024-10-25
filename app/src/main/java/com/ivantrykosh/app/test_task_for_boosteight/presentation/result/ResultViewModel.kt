package com.ivantrykosh.app.test_task_for_boosteight.presentation.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivantrykosh.app.test_task_for_boosteight.domain.model.HeartRate
import com.ivantrykosh.app.test_task_for_boosteight.domain.use_case.heart_rate.InsertHeartRateUseCase
import com.ivantrykosh.app.test_task_for_boosteight.presentation.State
import com.ivantrykosh.app.test_task_for_boosteight.utils.AppPreferences
import com.ivantrykosh.app.test_task_for_boosteight.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * ViewModel for Result screen
 */
@HiltViewModel
class ResultViewModel @Inject constructor(
    private val insertHeartRateUseCase: InsertHeartRateUseCase
) : ViewModel() {

    private val _insertHeartRate = MutableLiveData<State<Unit>>()
    val insertHeartRate: LiveData<State<Unit>> = _insertHeartRate

    fun insertHeartRate(heartRateValue: Int, heartRateTimeInSeconds: Long) {
        val heartRate = HeartRate(
            heartRate = heartRateValue,
            dateTime = heartRateTimeInSeconds
        )
        insertHeartRateUseCase(heartRate).onEach { resource ->
            when (resource) {
                is Resource.Error -> _insertHeartRate.value = State(error = resource.message)
                is Resource.Loading -> _insertHeartRate.value = State(loading = true)
                is Resource.Success -> {
                    AppPreferences.isFirstMeasurement = false
                    _insertHeartRate.value = State(data = resource.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}