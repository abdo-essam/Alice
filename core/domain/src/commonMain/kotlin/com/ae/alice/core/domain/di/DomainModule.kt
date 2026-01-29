package com.ae.alice.core.domain.di

import org.koin.dsl.module
import com.ae.alice.core.domain.usecase.GetBrandsUseCase
import com.ae.alice.core.domain.usecase.GetFavoritesUseCase
import com.ae.alice.core.domain.usecase.GetModelsUseCase
import com.ae.alice.core.domain.usecase.IsFavoriteUseCase
import com.ae.alice.core.domain.usecase.SearchUseCase
import com.ae.alice.core.domain.usecase.ToggleFavoriteUseCase

/**
 * Koin module for domain layer dependencies (use cases)
 */
val domainModule = module {
    
    // Brand Use Cases
    factory { GetBrandsUseCase(get()) }
    
    // Model Use Cases
    factory { GetModelsUseCase(get()) }
    
    // Search Use Cases
    factory { SearchUseCase(get(), get()) }
    
    // Favorite Use Cases
    factory { ToggleFavoriteUseCase(get()) }
    factory { GetFavoritesUseCase(get()) }
    factory { IsFavoriteUseCase(get()) }
}
