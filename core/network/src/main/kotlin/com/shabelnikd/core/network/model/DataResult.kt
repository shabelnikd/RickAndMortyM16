package com.shabelnikd.core.network.model

import kotlinx.serialization.Serializable


@Serializable
sealed interface DataResult<out D, out E : AppError> {

    @Serializable
    data class Success<out D>(val data: D) : DataResult<D, Nothing>

    @Serializable
    data class Error<out E : AppError>(val error: E) : DataResult<Nothing, E>

    val isSuccess: Boolean get() = this is Success
    val isError: Boolean get() = this is Error

    fun getOrNull(): D? = (this as? Success)?.data
    fun errorOrNull(): E? = (this as? Error)?.error
}

inline fun <T, R, E : AppError> DataResult<T, E>.map(transform: (T) -> R): DataResult<R, E> {
    return when (this) {
        is DataResult.Success -> DataResult.Success(transform(data))
        is DataResult.Error -> this
    }
}


inline fun <T, E : AppError> DataResult<T, E>.onSuccess(action: (T) -> Unit): DataResult<T, E> {
    if (this is DataResult.Success) action(data)
    return this
}

inline fun <T, E : AppError> DataResult<T, E>.onError(action: (E) -> Unit): DataResult<T, E> {
    if (this is DataResult.Error) action(error)
    return this
}



