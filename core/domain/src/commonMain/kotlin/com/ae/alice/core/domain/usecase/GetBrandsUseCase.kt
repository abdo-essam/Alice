package com.ae.alice.core.domain.usecase

import com.ae.alice.core.common.result.Result
import com.ae.alice.core.domain.entity.BrandsList
import com.ae.alice.core.domain.repository.BrandRepository

/**
 * Use case for getting all car brands.
 */
class GetBrandsUseCase(
    private val brandRepository: BrandRepository
) {
    suspend operator fun invoke(): Result<BrandsList> {
        return brandRepository.getBrands()
    }
    
    suspend operator fun invoke(page: Int, pageSize: Int): Result<BrandsList> {
        return brandRepository.getBrands(page, pageSize)
    }
}
