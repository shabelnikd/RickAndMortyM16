package com.shabelnikd.core.network.di

import com.shabelnikd.core.network.util.configureCore
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.logging.Logger
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    single(named("baseUrl")) { "https://rickandmortyapi.com/api/" }

    single { OkHttp.create() }

    single {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
            prettyPrint = true
            encodeDefaults = false
            explicitNulls = false
        }
    }


    single<Logger> {
        object : Logger {
            override fun log(message: String) {
                println(message)
            }
        }
    }


    single {
        HttpClient(engine = get()) {
            configureCore(
                json = get(),
                loggerDelegate = get(),
                baseUrl = get(named("baseUrl"))
            )
        }
    }
}