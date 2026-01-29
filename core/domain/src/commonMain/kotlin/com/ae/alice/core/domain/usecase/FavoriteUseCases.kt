package com.ae.alice.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import com.ae.alice.core.common.result.Result
import com.ae.alice.core.domain.entity.CarModel
import com.ae.alice.core.domain.repository.FavoritesRepository

/**
 * Use case for toggling favorite status of a car model.
 */
class ToggleFavoriteUseCase(
    private val favoritesRepository: FavoritesRepository
) {
    /**
     * Toggle favorite status and return new state
     */
    suspend operator fun invoke(model: CarModel): Result<Boolean> {
        return favoritesRepository.toggleFavorite(model)
    }
}

/**
 * Use case for getting all favorites.
 */
class GetFavoritesUseCase(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(): Result<List<CarModel>> {
        return favoritesRepository.getFavorites()
    }
    
    fun observeFavorites(): Flow<List<CarModel>> {
        return favoritesRepository.observeFavorites()
    }
    
    fun observeFavoriteIds(): Flow<Set<String>> {
        return favoritesRepository.observeFavoriteIds()
    }
}

/**
 * Use case for checking if a model is favorited.
 */
class IsFavoriteUseCase(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(modelId: String): Result<Boolean> {
        return favoritesRepository.isFavorite(modelId)
    }
}
