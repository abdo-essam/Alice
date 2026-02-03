package com.ae.alice.core.domain.repository

import kotlinx.coroutines.flow.Flow
import com.ae.alice.core.domain.entity.CarModel

/**
 * Repository interface for car model operations.
 * Implementation in data layer.
 */
interface CarModelRepository {
    suspend fun getModelsByBrand(brandId: String): List<CarModel>
    suspend fun getModelById(id: String): CarModel?
    suspend fun searchModels(query: String): List<CarModel>
    fun observeModelsByBrand(brandId: String): Flow<List<CarModel>>
}
