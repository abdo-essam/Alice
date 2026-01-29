package com.ae.alice.core.domain.repository

import kotlinx.coroutines.flow.Flow
import com.ae.alice.core.common.result.Result
import com.ae.alice.core.domain.entity.CarModel
import com.ae.alice.core.domain.entity.CarModelsList
import com.ae.alice.core.domain.entity.SearchFilters
import com.ae.alice.core.domain.entity.SortOption

/**
 * Repository interface for car model operations.
 * Implementations in the data layer.
 */
interface CarModelRepository {
    
    /**
     * Get all models for a brand
     */
    suspend fun getModelsByBrand(brandId: String): Result<CarModelsList>
    
    /**
     * Get models with pagination
     */
    suspend fun getModelsByBrand(
        brandId: String, 
        page: Int, 
        pageSize: Int
    ): Result<CarModelsList>
    
    /**
     * Get a single model by ID
     */
    suspend fun getModelById(id: String): Result<CarModel>
    
    /**
     * Search models by query
     */
    suspend fun searchModels(query: String): Result<List<CarModel>>
    
    /**
     * Get models with filters
     */
    suspend fun getModelsWithFilters(
        filters: SearchFilters,
        sortOption: SortOption = SortOption.NAME_ASC
    ): Result<List<CarModel>>
    
    /**
     * Observe models for a brand as a Flow
     */
    fun observeModelsByBrand(brandId: String): Flow<Result<CarModelsList>>
}
