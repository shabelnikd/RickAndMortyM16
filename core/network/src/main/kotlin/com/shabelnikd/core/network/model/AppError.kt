package com.shabelnikd.core.network.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException

@Serializable
sealed interface AppError {

    sealed interface Network : AppError {
        @Serializable data object NoInternet : Network
        @Serializable data object Timeout : Network
        @Serializable data object ServerUnavailable : Network
        @Serializable data object Serialization : Network
        @Serializable data class Unknown(val message: String) : Network
    }

    sealed interface Api : AppError {
        @Serializable data class Unauthorized(val message: String) : Api // 401
        @Serializable data class Forbidden(val message: String) : Api // 403
        @Serializable data class NotFound(val message: String) : Api // 404
        @Serializable data class Conflict(val message: String) : Api // 409
        @Serializable data class TooManyRequests(val message: String): Api // 429
        @Serializable data class BadRequest(val message: String) : Api // 400/422
        @Serializable data class ServerError(val code: Int, val message: String?) : Api // 500
    }

    @Serializable data class Unknown(val message: String?) : AppError

    fun asThrowable(): Throwable = when (this) {
        // --- Сетевые ошибки ---
        is Network.NoInternet ->
            kotlinx.io.IOException("Отсутствует подключение к интернету. Проверьте настройки сети.")

        is Network.Timeout ->
            Exception("Превышено время ожидания ответа от сервера. Повторите попытку.")

        is Network.ServerUnavailable ->
            kotlinx.io.IOException("Сервер временно недоступен. Пожалуйста, попробуйте позже.")

        is Network.Serialization ->
            SerializationException("Ошибка обработки данных. Не удалось прочитать ответ сервера.")

        is Network.Unknown ->
            Exception("Неизвестная сетевая ошибка: $message")

        // --- Ошибки API (HTTP) ---
        is Api.Unauthorized ->
            Exception("Ошибка авторизации (401): ${message.ifBlank { "Пожалуйста, войдите в аккаунт заново." }}")

        is Api.Forbidden ->
            SecurityException("Доступ запрещен (403): ${message.ifBlank { "У вас нет прав для выполнения этой операции." }}")

        is Api.NotFound ->
            NoSuchElementException("Ресурс не найден (404): ${message.ifBlank { "Запрашиваемые данные не существуют." }}")

        is Api.Conflict ->
            IllegalStateException("Конфликт данных (409): ${message.ifBlank { "Операция отменена из-за конфликта состояний." }}")

        is Api.TooManyRequests ->
            Exception("Слишком много запросов (429): ${message.ifBlank { "Пожалуйста, подождите немного перед следующей попыткой." }}")

        is Api.BadRequest ->
            IllegalArgumentException("Неверный запрос (400): ${message.ifBlank { "Проверьте введенные данные." }}")

        is Api.ServerError ->
            Exception("Внутренняя ошибка сервера (Код $code): ${message ?: "Что-то пошло не так на стороне бэкенда."}")

        // --- Неизвестные ошибки ---
        is Unknown ->
            Exception("Неизвестная ошибка приложения: ${message ?: "Без описания"}")
    }
}