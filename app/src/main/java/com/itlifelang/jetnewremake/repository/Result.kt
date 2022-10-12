package com.itlifelang.jetnewremake.repository

/**
 * A sealed class that handles data and its loading states.
 */
sealed class Result<out R> {
    object Loading : Result<Nothing>()

    data class Success<out T>(val data: T) : Result<T>()

    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            else -> "Loading"
        }
    }
}
