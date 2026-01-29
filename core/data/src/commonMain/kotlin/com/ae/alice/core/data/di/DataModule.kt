package com.ae.alice.core.data.di

import org.koin.dsl.module
import com.ae.alice.core.data.datasource.CarApiService
import com.ae.alice.core.data.repository.BrandRepositoryImpl
import com.ae.alice.core.data.repository.CarModelRepositoryImpl
import com.ae.alice.core.data.repository.FavoritesRepositoryImpl
import com.ae.alice.core.domain.repository.BrandRepository
import com.ae.alice.core.domain.repository.CarModelRepository
import com.ae.alice.core.domain.repository.FavoritesRepository

/**
 * Koin module for data layer dependencies
 */
val dataModule = module {
    
    // Data Sources
    single { CarApiService(get()) }
    
    // Repositories
    single<FavoritesRepository> { FavoritesRepositoryImpl(get(), get()) }
    single<BrandRepository> { BrandRepositoryImpl(get()) }
    single<CarModelRepository> { CarModelRepositoryImpl(get(), get()) }
}
