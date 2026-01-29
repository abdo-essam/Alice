package com.ae.alice.core.domain.repository

import kotlinx.coroutines.flow.Flow
import com.ae.alice.core.common.result.Result
import com.ae.alice.core.domain.entity.Brand
import com.ae.alice.core.domain.entity.BrandsList

/**
 * Repository interface for brand-related operations.
 * Implementations in the data layer.
 */
interface BrandRepository {
    
    /**
     * Get all brands
     */
    suspend fun getBrands(): Result<BrandsList>
    
    /**
     * Get brands with pagination
     */
    suspend fun getBrands(page: Int, pageSize: Int): Result<BrandsList>
    
    /**
     * Get a single brand by ID
     */
    suspend fun getBrandById(id: String): Result<Brand>
    
    /**
     * Search brands by query
     */
    suspend fun searchBrands(query: String): Result<List<Brand>>
    
    /**
     * Observe brands as a Flow (for reactive updates)
     */
    fun observeBrands(): Flow<Result<BrandsList>>
}
