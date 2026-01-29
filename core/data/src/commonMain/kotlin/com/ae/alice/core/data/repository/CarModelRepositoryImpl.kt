package com.ae.alice.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.ae.alice.core.common.base.safeApiCall
import com.ae.alice.core.common.result.Result
import com.ae.alice.core.data.datasource.CarApiService
import com.ae.alice.core.data.mapper.CarModelMapper.toDomain
import com.ae.alice.core.data.mapper.CarModelMapper.toDomainList
import com.ae.alice.core.domain.entity.CarModel
import com.ae.alice.core.domain.entity.CarModelsList
import com.ae.alice.core.domain.entity.SearchFilters
import com.ae.alice.core.domain.entity.SortOption
import com.ae.alice.core.domain.repository.CarModelRepository
import com.ae.alice.core.domain.repository.FavoritesRepository

/**
 * Implementation of CarModelRepository
 */
class CarModelRepositoryImpl(
    private val apiService: CarApiService,
    private val favoritesRepository: FavoritesRepository
) : CarModelRepository {
    
    override suspend fun getModelsByBrand(brandId: String): Result<CarModelsList> = safeApiCall {
        val favoriteIds = favoritesRepository.getFavoriteIds().getOrDefault(emptySet())
        apiService.getModelsByBrand(brandId).toDomain(favoriteIds)
    }
    
    override suspend fun getModelsByBrand(
        brandId: String,
        page: Int,
        pageSize: Int
    ): Result<CarModelsList> = safeApiCall {
        val favoriteIds = favoritesRepository.getFavoriteIds().getOrDefault(emptySet())
        apiService.getModelsByBrand(brandId, page, pageSize).toDomain(favoriteIds)
    }
    
    override suspend fun getModelById(id: String): Result<CarModel> = safeApiCall {
        val favoriteIds = favoritesRepository.getFavoriteIds().getOrDefault(emptySet())
        apiService.getModelById(id).toDomain(favoriteIds.contains(id))
    }
    
    override suspend fun searchModels(query: String): Result<List<CarModel>> = safeApiCall {
        val favoriteIds = favoritesRepository.getFavoriteIds().getOrDefault(emptySet())
        apiService.searchModels(query).toDomainList(favoriteIds)
    }
    
    override suspend fun getModelsWithFilters(
        filters: SearchFilters,
        sortOption: SortOption
    ): Result<List<CarModel>> = safeApiCall {
        val favoriteIds = favoritesRepository.getFavoriteIds().getOrDefault(emptySet())
        var models = apiService.searchModels(filters.query).toDomainList(favoriteIds)
        
        // Apply filters locally (in production, this would be server-side)
        if (filters.brandIds.isNotEmpty()) {
            models = models.filter { it.brandId in filters.brandIds }
        }
        if (filters.categories.isNotEmpty()) {
            models = models.filter { it.category in filters.categories }
        }
        if (filters.onlyFavorites) {
            models = models.filter { it.isFavorite }
        }
        
        // Apply sorting
        models = when (sortOption) {
            SortOption.NAME_ASC -> models.sortedBy { it.name }
            SortOption.NAME_DESC -> models.sortedByDescending { it.name }
            SortOption.YEAR_ASC -> models.sortedBy { it.year ?: 0 }
            SortOption.YEAR_DESC -> models.sortedByDescending { it.year ?: 0 }
            SortOption.PRICE_ASC -> models.sortedBy { it.price?.amount ?: 0.0 }
            SortOption.PRICE_DESC -> models.sortedByDescending { it.price?.amount ?: 0.0 }
            SortOption.POPULARITY -> models // Keep original order
        }
        
        models
    }
    
    override fun observeModelsByBrand(brandId: String): Flow<Result<CarModelsList>> = flow {
        emit(Result.Loading)
        emit(getModelsByBrand(brandId))
    }
}
