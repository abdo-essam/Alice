package com.ae.alice.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import com.ae.alice.core.data.di.dataModule
import com.ae.alice.core.domain.di.domainModule
import com.ae.alice.core.network.di.networkModule

/**
 * Initialize Koin dependency injection
 */
fun initKoin(
    platformModules: List<Module> = emptyList(),
    appDeclaration: KoinAppDeclaration = {}
) {
    startKoin {
        appDeclaration()
        modules(
            networkModule,
            dataModule,
            domainModule,
            *platformModules.toTypedArray()
        )
    }
}

/**
 * Common app module for shared dependencies
 */
val appModule = module {
    // App-level dependencies can be added here
}
