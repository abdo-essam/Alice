package com.ae.alice.network.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

/**
 * iOS-specific HttpClient factory using Darwin engine
 */
actual fun createPlatformHttpClient(
    baseUrl: String,
    enableLogging: Boolean
): HttpClient {
    val engine = Darwin.create {
        configureRequest {
            setAllowsCellularAccess(true)
        }
    }
    
    return KtorClientFactory.create(
        engine = engine,
        baseUrl = baseUrl,
        enableLogging = enableLogging
    )
}
