package com.ae.alice.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.ae.alice.core.common.base.safeApiCall
import com.ae.alice.core.common.result.Result
import com.ae.alice.core.data.datasource.CarApiService
import com.ae.alice.core.data.mapper.BrandMapper.toDomain
import com.ae.alice.core.data.mapper.BrandMapper.toDomainList
import com.ae.alice.core.domain.entity.Brand
import com.ae.alice.core.domain.entity.BrandsList
import com.ae.alice.core.domain.repository.BrandRepository

/**
 * Implementation of BrandRepository
 */
class BrandRepositoryImpl(
    private val apiService: CarApiService
) : BrandRepository {
    
    override suspend fun getBrands(): Result<BrandsList> = safeApiCall {
        apiService.getBrands().toDomain()
    }
    
    override suspend fun getBrands(page: Int, pageSize: Int): Result<BrandsList> = safeApiCall {
        apiService.getBrands(page, pageSize).toDomain()
    }
    
    override suspend fun getBrandById(id: String): Result<Brand> = safeApiCall {
        apiService.getBrandById(id).toDomain()
    }
    
    override suspend fun searchBrands(query: String): Result<List<Brand>> = safeApiCall {
        apiService.searchBrands(query).toDomainList()
    }
    
    override fun observeBrands(): Flow<Result<BrandsList>> = flow {
        emit(Result.Loading)
        emit(getBrands())
    }
}
