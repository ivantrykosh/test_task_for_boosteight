package com.ivantrykosh.app.test_task_for_boosteight.presentation

/**
 * State of process
 * @param loading if resource is loading, state of process is true
 * @param error if resource returns error message, state of process is error with this message
 * @param data if resource successfully returns some data (may be null), state of process contains this data
 */
data class State<T>(
    val loading: Boolean = false,
    val error: String = "",
    val data: T? = null
)
