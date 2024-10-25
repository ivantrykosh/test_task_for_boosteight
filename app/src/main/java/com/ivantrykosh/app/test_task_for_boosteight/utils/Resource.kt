package com.ivantrykosh.app.test_task_for_boosteight.utils

/**
 * Resource class that represents state of some resource
 * @param T type of resource
 */
sealed class Resource<T> {

    /**
     * Represents successful state with optional data
     * @param data returned data or null
     */
    class Success<T>(val data: T? = null) : Resource<T>()

    /**
     * Represents error state with message
     * @param message error message
     */
    class Error<T>(val message: String) : Resource<T>()

    /**
     * Represents loading state
     */
    class Loading<T> : Resource<T>()
}