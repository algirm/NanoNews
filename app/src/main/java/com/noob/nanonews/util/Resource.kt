package com.noob.nanonews.util

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure(val throwable: Throwable?) : Resource<Nothing>()
    object Empty : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}
