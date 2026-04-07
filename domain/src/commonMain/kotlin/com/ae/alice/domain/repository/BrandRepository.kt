package com.ae.alice.domain.repository

import kotlinx.coroutines.flow.Flow
import com.ae.alice.domain.entity.Brand

interface BrandRepository {
    suspend fun getBrands(): List<Brand>
    suspend fun getBrandById(id: String): Brand?
    suspend fun searchBrands(query: String): List<Brand>
    fun observeBrands(): Flow<List<Brand>>
}
