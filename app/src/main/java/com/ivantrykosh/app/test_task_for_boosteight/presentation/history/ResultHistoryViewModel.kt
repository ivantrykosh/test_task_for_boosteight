package com.ivantrykosh.app.test_task_for_boosteight.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivantrykosh.app.test_task_for_boosteight.domain.model.HeartRate
import com.ivantrykosh.app.test_task_for_boosteight.domain.use_case.heart_rate.DeleteAllHeartRatesUseCase
import com.ivantrykosh.app.test_task_for_boosteight.domain.use_case.heart_rate.GetAllHeartRatesUseCase
import com.ivantrykosh.app.test_task_for_boosteight.presentation.State
import com.ivantrykosh.app.test_task_for_boosteight.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ResultHistoryViewModel @Inject constructor(
    private val getAllHeartRatesUseCase: GetAllHeartRatesUseCase,
    private val deleteAllHeartRatesUseCase: DeleteAllHeartRatesUseCase
) : ViewModel() {

    private val _getAllHeartRates = MutableLiveData<State<List<HeartRate>>>()
    val getAllHeartRates: LiveData<State<List<HeartRate>>> = _getAllHeartRates

    private val _deleteAllHeartRates = MutableLiveData<State<Unit>>()
    val deleteAllHeartRates: LiveData<State<Unit>> = _deleteAllHeartRates

    fun getAllHeartRates() {
        getAllHeartRatesUseCase().onEach { resource ->
            when (resource) {
                is Resource.Error -> _getAllHeartRates.value = State(error = resource.message)
                is Resource.Loading -> _getAllHeartRates.value = State(loading = true)
                is Resource.Success -> _getAllHeartRates.value = State(data = resource.data ?: emptyList())
            }
        }.launchIn(viewModelScope)
    }

    fun deleteAllHeartRates() {
        deleteAllHeartRatesUseCase().onEach { resource ->
            when (resource) {
                is Resource.Error -> _deleteAllHeartRates.value = State(error = resource.message)
                is Resource.Loading -> _deleteAllHeartRates.value = State(loading = true)
                is Resource.Success -> _deleteAllHeartRates.value = State()
            }
        }.launchIn(viewModelScope)
    }
}