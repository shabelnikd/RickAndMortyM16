package com.shabelnikd.core.network.util

import com.shabelnikd.core.network.model.AppError
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.JsonConvertException
import kotlinx.io.IOException
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException

@Serializable
data class ApiErrorResponseDto(
    val message: String? = null,
    val detail: String? = null
) {
    val userMessage: String
        get() = message
            ?: detail
            ?: "Unknown json key"
}

suspend fun mapExceptionToAppError(e: Exception): AppError {
    return when (e) {
        is ClientRequestException -> {
            val status = e.response.status.value
            var message: String? = null

            try {
                val dto = e.response.body<ApiErrorResponseDto>()
                message = dto.userMessage
            } catch (_: Exception) {
                message = try {
                    e.response.bodyAsText()
                } catch (_: Exception) {
                    null
                }
            }

            when (status) {
                400 -> AppError.Api.BadRequest(message ?: "Проверьте правильность данных")
                401 -> AppError.Api.Unauthorized(message ?: "Пользователь не авторизован")
                403 -> AppError.Api.Forbidden(message ?: "Для этого пользователя доступ запрещен")
                404 -> AppError.Api.NotFound(message ?: "Ничего не найдено")
                409 -> AppError.Api.Conflict(message ?: "Конфликт данных на сервере")
                422 -> AppError.Api.BadRequest(message ?: "Проверьте правильность полей")
                429 -> AppError.Api.TooManyRequests(message ?: "Слишком много запросов. Пожалуйста, подождите.")
                else -> AppError.Api.BadRequest("Error $status: $message")
            }
        }
        is ServerResponseException -> {
            val status = e.response.status.value
            if (status == 503) AppError.Network.ServerUnavailable
            else AppError.Api.ServerError(status, e.message)
        }

        is HttpRequestTimeoutException -> AppError.Network.Timeout
        is RedirectResponseException -> AppError.Network.Unknown("Redirect error: ${e.message}")
        is JsonConvertException,
        is NoTransformationFoundException,
        is SerializationException -> AppError.Network.Serialization

        is IOException -> AppError.Network.NoInternet

        else -> AppError.Unknown(e.message)

    }
}