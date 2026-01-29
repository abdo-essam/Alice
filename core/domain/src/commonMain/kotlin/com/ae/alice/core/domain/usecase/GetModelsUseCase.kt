package com.ae.alice.core.domain.usecase

import com.ae.alice.core.common.result.Result
import com.ae.alice.core.domain.entity.CarModelsList
import com.ae.alice.core.domain.repository.CarModelRepository

/**
 * Use case for getting car models by brand.
 */
class GetModelsUseCase(
    private val carModelRepository: CarModelRepository
) {
    suspend operator fun invoke(brandId: String): Result<CarModelsList> {
        return carModelRepository.getModelsByBrand(brandId)
    }
    
    suspend operator fun invoke(
        brandId: String,
        page: Int,
        pageSize: Int
    ): Result<CarModelsList> {
        return carModelRepository.getModelsByBrand(brandId, page, pageSize)
    }
}
