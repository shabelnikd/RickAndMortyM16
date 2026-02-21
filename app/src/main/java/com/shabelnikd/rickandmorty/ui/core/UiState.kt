package com.shabelnikd.rickandmorty.ui.core

import com.shabelnikd.core.network.model.AppError
import com.shabelnikd.core.network.model.DataResult
import com.shabelnikd.core.network.model.onError
import com.shabelnikd.core.network.model.onSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

sealed interface UiState<out T> {
    data object NotLoaded : UiState<Nothing>
    data object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>
}

fun <T, E: AppError> Flow<DataResult<T, E>>.asUiState(
    scope: CoroutineScope,
    initialValue: UiState<T> = UiState.NotLoaded
) : StateFlow<UiState<T>> {
    return this
        .map { result ->
            when (result) {
                is DataResult.Success -> UiState.Success(result.data)
                is DataResult.Error -> UiState.Error(
                    message = result.error.asThrowable().message ?: ""
                )
            }
        }.onStart { emit(UiState.Loading) }
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = initialValue
        )
}
