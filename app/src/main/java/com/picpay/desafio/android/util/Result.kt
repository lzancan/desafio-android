package com.picpay.desafio.android.util

sealed class Result<out D, out E> {
    data class Success<D>(val value: D) : Result<D, Nothing>()
    data class Error<E>(val value: E) : Result<Nothing, E>()

    inline fun <T> mapSuccess(transform: (D) -> T): Result<T, E> = when (this) {
        is Success -> Success(transform(value))
        is Error -> Error(value)
    }

    inline fun <T> mapError(transform: (E) -> T): Result<D, T> = when (this) {
        is Success -> Success(value)
        is Error -> Error(transform(value))
    }

    inline fun onSuccess(block: (D) -> Unit): Result<D, E> {
        if (this is Success) block(value)
        return this
    }

    inline fun onError(block: (E) -> Unit): Result<D, E> {
        if (this is Error) block(value)
        return this
    }

    fun get(): D? {
        return when (this) {
            is Success -> value
            is Error -> null
        }
    }
}