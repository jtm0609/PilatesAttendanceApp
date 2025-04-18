package com.example.domain.dataresource

sealed class DataResource<out T> {
    data class Success<out T>(val data: T) : DataResource<T>()
    data class Error(val throwable: Throwable) : DataResource<Nothing>()
    data object Loading : DataResource<Nothing>()

    companion object {
        fun <T> success(data: T) = Success(data)
        fun error(throwable: Throwable) = Error(throwable)
        fun loading() = Loading
    }
}