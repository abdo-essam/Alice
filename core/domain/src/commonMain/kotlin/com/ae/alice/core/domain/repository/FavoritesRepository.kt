package com.ae.alice.core.domain.repository

import kotlinx.coroutines.flow.Flow
import com.ae.alice.core.common.result.Result
import com.ae.alice.core.domain.entity.CarModel

/**
 * Repository interface for favorites operations.
 * Uses local storage (DataStore) for persistence.
 */
interface FavoritesRepository {
    
    /**
     * Get all favorite model IDs
     */
    suspend fun getFavoriteIds(): Result<Set<String>>
    
    /**
     * Get all favorite models
     */
    suspend fun getFavorites(): Result<List<CarModel>>
    
    /**
     * Check if a model is favorited
     */
    suspend fun isFavorite(modelId: String): Result<Boolean>
    
    /**
     * Add a model to favorites
     */
    suspend fun addToFavorites(model: CarModel): Result<Unit>
    
    /**
     * Remove a model from favorites
     */
    suspend fun removeFromFavorites(modelId: String): Result<Unit>
    
    /**
     * Toggle favorite status
     */
    suspend fun toggleFavorite(model: CarModel): Result<Boolean>
    
    /**
     * Observe favorite IDs as a Flow
     */
    fun observeFavoriteIds(): Flow<Set<String>>
    
    /**
     * Observe all favorites as a Flow
     */
    fun observeFavorites(): Flow<List<CarModel>>
    
    /**
     * Clear all favorites
     */
    suspend fun clearAllFavorites(): Result<Unit>
}
