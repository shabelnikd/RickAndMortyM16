package com.shabelnikd.core.network.util

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun HttpClientConfig<*>.configureCore(
    json: Json,
    loggerDelegate: Logger,
    baseUrl: String? = null,
) {
    expectSuccess = true

    install(Logging) {
        logger = loggerDelegate
        level = LogLevel.ALL
    }

    install(ContentNegotiation) {
        json(json)
    }

    install(HttpTimeout) {
        requestTimeoutMillis = 30_000
        connectTimeoutMillis = 15_000
        socketTimeoutMillis = 30_000
    }

    if (baseUrl != null) {
        defaultRequest {
            url(baseUrl)
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }

}