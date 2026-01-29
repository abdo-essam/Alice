package com.ae.alice.core.network.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Ktor HTTP client configuration for the Alice app.
 * Platform-specific engines are configured in androidMain/iosMain.
 */
object KtorClientFactory {
    
    /**
     * JSON serialization configuration
     */
    val json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
        coerceInputValues = true
        encodeDefaults = true
    }
    
    /**
     * Creates a configured HttpClient
     * @param engine Platform-specific engine (OkHttp for Android, Darwin for iOS)
     * @param baseUrl Base URL for API requests
     */
    fun create(
        engine: io.ktor.client.engine.HttpClientEngine,
        baseUrl: String,
        enableLogging: Boolean = true
    ): HttpClient {
        return HttpClient(engine) {
            // JSON Serialization
            install(ContentNegotiation) {
                json(json)
            }
            
            // Logging (for debug builds)
            if (enableLogging) {
                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            println("ALICE_HTTP: $message")
                        }
                    }
                    level = LogLevel.BODY
                }
            }
            
            // Default request configuration
            defaultRequest {
                url(baseUrl)
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header(HttpHeaders.Accept, ContentType.Application.Json)
            }
            
            // Timeout configuration is handled per-request or via engine config
        }
    }
}
