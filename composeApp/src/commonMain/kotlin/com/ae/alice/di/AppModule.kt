package com.ae.alice.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import com.ae.alice.data.di.dataModule
import com.ae.alice.presentation.screens.brands.BrandsViewModel
import com.ae.alice.presentation.screens.cardetails.CarDetailsViewModel
import com.ae.alice.presentation.screens.models.ModelsViewModel
import com.ae.alice.presentation.screens.places.PlacesViewModel
import com.ae.alice.presentation.screens.profile.ProfileViewModel
import com.ae.alice.presentation.screens.auth.login.LoginViewModel
import com.ae.alice.presentation.screens.auth.register.RegisterViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel

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

val appModule = module {
    viewModel { BrandsViewModel(get()) }
    viewModel { ModelsViewModel(get()) }
    viewModel { CarDetailsViewModel(get()) }
    viewModel { PlacesViewModel(get(), get()) }
    viewModel { ProfileViewModel() }
    viewModel { com.ae.alice.presentation.screens.main.MainViewModel(get(), get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
}
