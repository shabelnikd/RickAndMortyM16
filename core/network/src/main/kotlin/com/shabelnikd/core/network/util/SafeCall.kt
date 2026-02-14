package com.shabelnikd.core.network.util

import com.shabelnikd.core.network.model.AppError
import com.shabelnikd.core.network.model.DataResult
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext

suspend inline fun <reified T> safeCall(
    dispatcher: CoroutineDispatcher,
    crossinline execute: suspend () -> HttpResponse
): DataResult<T, AppError> {
    return withContext(dispatcher) {
        try {
            DataResult.Success(execute().body<T>())
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            currentCoroutineContext().ensureActive()
            DataResult.Error(mapExceptionToAppError(e))
        }
    }
}