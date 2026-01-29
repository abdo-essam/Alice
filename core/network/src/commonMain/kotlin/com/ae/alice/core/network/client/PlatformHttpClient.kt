package com.ae.alice.core.network.client

import io.ktor.client.HttpClient

/**
 * Platform-specific HttpClient factory.
 * Implemented in androidMain and iosMain with platform-specific engines.
 */
expect fun createPlatformHttpClient(
    baseUrl: String,
    enableLogging: Boolean = true
): HttpClient
