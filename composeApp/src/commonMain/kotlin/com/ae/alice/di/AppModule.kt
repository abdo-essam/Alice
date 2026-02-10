package com.ae.alice.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import com.ae.alice.data.di.dataModule
import com.ae.alice.screens.brands.BrandsViewModel
import com.ae.alice.screens.models.ModelsViewModel
import org.koin.core.module.dsl.viewModel

/**
 * Initialize Koin dependency injection.
 */
fun initKoin(
    platformModules: List<Module> = emptyList(),
    appDeclaration: KoinAppDeclaration = {}
) {
    startKoin {
        appDeclaration()
        modules(
            dataModule,
            appModule,
            *platformModules.toTypedArray()
        )
    }
}

/**
 * App module with ViewModels.
 */
val appModule = module {
    viewModel { BrandsViewModel(get()) }
    viewModel { ModelsViewModel(get()) }
}
