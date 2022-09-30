package com.itlifelang.jetnewremake.ui.util

import com.itlifelang.jetnewremake.repository.Result

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()

    data class Success<T>(val data: T) : UiState<T>()

    data class Error(val msg: String?) : UiState<Nothing>()
}

fun <T> Result<T>.toUiState(): UiState<T> {
    return when (this) {
        is Result.Loading -> UiState.Loading
        is Result.Success -> UiState.Success(data)
        is Result.Error -> UiState.Error(exception.message)
    }
}

fun <T, R> Result<T>.toUiState(transform: (T) -> R): UiState<R> {
    return when (this) {
        is Result.Loading -> UiState.Loading
        is Result.Success -> UiState.Success(transform(data))
        is Result.Error -> UiState.Error(exception.message)
    }
}
