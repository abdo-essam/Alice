package com.ae.alice.core.domain.usecase

import com.ae.alice.core.common.result.Result
import com.ae.alice.core.domain.entity.Brand
import com.ae.alice.core.domain.entity.CarModel
import com.ae.alice.core.domain.entity.SearchResult
import com.ae.alice.core.domain.repository.BrandRepository
import com.ae.alice.core.domain.repository.CarModelRepository

/**
 * Use case for searching brands and models.
 */
class SearchUseCase(
    private val brandRepository: BrandRepository,
    private val carModelRepository: CarModelRepository
) {
    /**
     * Search both brands and models
     */
    suspend operator fun invoke(query: String): Result<SearchResult> {
        if (query.isBlank()) {
            return Result.Success(SearchResult(
                brands = emptyList(),
                models = emptyList(),
                query = query
            ))
        }
        
        val brandsResult = brandRepository.searchBrands(query)
        val modelsResult = carModelRepository.searchModels(query)
        
        val brands = when (brandsResult) {
            is Result.Success -> brandsResult.data
            else -> emptyList()
        }
        
        val models = when (modelsResult) {
            is Result.Success -> modelsResult.data
            else -> emptyList()
        }
        
        return Result.Success(SearchResult(
            brands = brands,
            models = models,
            query = query
        ))
    }
    
    /**
     * Search only brands
     */
    suspend fun searchBrands(query: String): Result<List<Brand>> {
        return brandRepository.searchBrands(query)
    }
    
    /**
     * Search only models
     */
    suspend fun searchModels(query: String): Result<List<CarModel>> {
        return carModelRepository.searchModels(query)
    }
}
