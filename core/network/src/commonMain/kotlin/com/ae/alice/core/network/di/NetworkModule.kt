package com.ae.alice.core.network.di

import org.koin.dsl.module
import com.ae.alice.core.network.client.KtorClientFactory

/**
 * Koin module for network layer dependencies
 */
val networkModule = module {
    
    // JSON serializer (shared across the app)
    single { KtorClientFactory.json }
    
    // HttpClient is created in platform-specific modules 
    // with platform-specific engines (OkHttp for Android, Darwin for iOS)
}
