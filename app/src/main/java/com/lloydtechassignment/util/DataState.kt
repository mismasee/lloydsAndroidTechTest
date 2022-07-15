package com.lloydtechassignment.util

/**
 *Data State Management for UI & Data.
 * [Success] return the success state along with [data].
 * [Failure] return the failed state along with exception.
 * [Loading] return the loading state of data it returns nothing.
 */
sealed class DataState<out T> {
    class Success<T>(val data: T?) : DataState<T>()
    class Failure(val error: Throwable) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}