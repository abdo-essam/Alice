package com.ae.alice.domain.repository

import com.ae.alice.domain.entity.CarModel

interface CarModelRepository {
    suspend fun getModelsByBrand(brandId: String): List<CarModel>
    suspend fun getModelById(id: String): CarModel?
    suspend fun searchModels(query: String): List<CarModel>
}
