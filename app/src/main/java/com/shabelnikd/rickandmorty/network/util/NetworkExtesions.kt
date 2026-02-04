package com.shabelnikd.rickandmorty.network.util

import com.shabelnikd.rickandmorty.network.model.AppError
import com.shabelnikd.rickandmorty.network.model.DataResult
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.CoroutineDispatcher

suspend inline fun <reified T> HttpClient.safeGet(
   url: String,
   dispatcher: CoroutineDispatcher,
   crossinline builder: HttpRequestBuilder.() -> Unit = {}
): DataResult<T, AppError> {
    return safeCall(dispatcher) {
        this.get(url) {
            contentType(ContentType.Application.Json)
            builder()
        }
    }
}

suspend inline fun <reified Body, reified Response> HttpClient.safePost(
    url: String,
    body: Body,
    dispatcher: CoroutineDispatcher,
    crossinline builder: HttpRequestBuilder.() -> Unit = {}
): DataResult<Response, AppError> {
    return safeCall(dispatcher) {
        this.post(url) {
            contentType(ContentType.Application.Json)
            setBody(body)
            builder()
        }
    }
}

suspend inline fun <reified Response> HttpClient.safePostMultipart(
    url: String,
    body: MultiPartFormDataContent,
    dispatcher: CoroutineDispatcher,
    crossinline builder: HttpRequestBuilder.() -> Unit = {}
): DataResult<Response, AppError> {
    return safeCall(dispatcher) {
        this.post(url) {
            setBody(body)
            builder()
        }
    }
}

suspend inline fun <reified Body, reified Response> HttpClient.safePut(
    url: String,
    body: Body,
    dispatcher: CoroutineDispatcher,
    crossinline builder: HttpRequestBuilder.() -> Unit = {}
): DataResult<Response, AppError> {
    return safeCall(dispatcher) {
        this.put(url) {
            contentType(ContentType.Application.Json)
            setBody(body)
            builder()
        }
    }
}

suspend inline fun <reified Body, reified Response> HttpClient.safePatch(
    url: String,
    body: Body,
    dispatcher: CoroutineDispatcher,
    crossinline builder: HttpRequestBuilder.() -> Unit = {}
): DataResult<Response, AppError> {
    return safeCall(dispatcher) {
        this.patch(url) {
            contentType(ContentType.Application.Json)
            setBody(body)
            builder()
        }
    }
}


suspend inline fun <reified Body, reified Response> HttpClient.safePatchMultipart(
    url: String,
    body: Body,
    dispatcher: CoroutineDispatcher,
    crossinline builder: HttpRequestBuilder.() -> Unit = {}
): DataResult<Response, AppError> {
    return safeCall(dispatcher) {
        this.patch(url) {
            setBody(body)
            builder()
        }
    }
}


suspend inline fun <reified Response> HttpClient.safeDelete(
    url: String,
    dispatcher: CoroutineDispatcher,
    crossinline builder: HttpRequestBuilder.() -> Unit = {}
): DataResult<Response, AppError> {
    return safeCall(dispatcher) {
        this.delete(url) {
            contentType(ContentType.Application.Json)
            builder()
        }
    }
}