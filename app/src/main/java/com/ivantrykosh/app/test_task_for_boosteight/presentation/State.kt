package com.ivantrykosh.app.test_task_for_boosteight.presentation

data class State<T>(
    val loading: Boolean = false,
    val error: String = "",
    val data: T? = null
)
