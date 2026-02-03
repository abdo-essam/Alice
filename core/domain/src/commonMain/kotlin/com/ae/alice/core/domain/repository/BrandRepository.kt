package com.ae.alice.core.domain.repository

import kotlinx.coroutines.flow.Flow
import com.ae.alice.core.domain.entity.Brand

/**
 * Repository interface for brand operations.
 * Implementation in data layer.
 */
interface BrandRepository {
    suspend fun getBrands(): List<Brand>
    suspend fun getBrandById(id: String): Brand?
    suspend fun searchBrands(query: String): List<Brand>
    fun observeBrands(): Flow<List<Brand>>
}
