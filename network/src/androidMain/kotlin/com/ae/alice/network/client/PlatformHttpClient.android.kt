package com.ae.alice.network.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import java.util.concurrent.TimeUnit

/**
 * Android-specific HttpClient factory using OkHttp engine
 */
actual fun createPlatformHttpClient(
    baseUrl: String,
    enableLogging: Boolean
): HttpClient {
    val engine = OkHttp.create {
        config {
            connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        }
    }
    
    return KtorClientFactory.create(
        engine = engine,
        baseUrl = baseUrl,
        enableLogging = enableLogging
    )
}

private const val CONNECT_TIMEOUT_SECONDS = 30L
private const val READ_TIMEOUT_SECONDS = 30L
private const val WRITE_TIMEOUT_SECONDS = 30L
