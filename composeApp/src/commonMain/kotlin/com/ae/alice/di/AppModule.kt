package com.ae.alice.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import com.ae.alice.data.di.dataModule
import com.ae.alice.data.local.createDataStore
import com.ae.alice.data.repository.SettingsRepositoryImpl
import com.ae.alice.domain.repository.SettingsRepository
import com.ae.alice.domain.usecase.ManageLanguageUseCase
import com.ae.alice.domain.usecase.ManageThemeUseCase
import com.ae.alice.presentation.screens.brands.BrandsViewModel
import com.ae.alice.presentation.screens.cardetails.CarDetailsViewModel
import com.ae.alice.presentation.screens.models.ModelsViewModel
import com.ae.alice.presentation.screens.settings.SettingsViewModel
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
    single { createDataStore() }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
    single { ManageThemeUseCase(get()) }
    single { ManageLanguageUseCase(get()) }

    viewModel { BrandsViewModel(get()) }
    viewModel { ModelsViewModel(get()) }
    viewModel { CarDetailsViewModel(get()) }
    viewModel { SettingsViewModel(get(), get()) }
}
