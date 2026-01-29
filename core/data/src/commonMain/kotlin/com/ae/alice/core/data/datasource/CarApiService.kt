package com.ae.alice.core.data.datasource

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import com.ae.alice.core.data.dto.BrandDto
import com.ae.alice.core.data.dto.BrandsResponseDto
import com.ae.alice.core.data.dto.CarModelDto
import com.ae.alice.core.data.dto.ModelsResponseDto
import com.ae.alice.core.network.client.ApiEndpoints

/**
 * Remote data source for car-related API calls
 */
class CarApiService(
    private val httpClient: HttpClient
) {
    
    /**
     * Get all brands
     */
    suspend fun getBrands(): BrandsResponseDto {
        return httpClient.get(ApiEndpoints.Brands.ALL).body()
    }
    
    /**
     * Get brands with pagination
     */
    suspend fun getBrands(page: Int, pageSize: Int): BrandsResponseDto {
        return httpClient.get(ApiEndpoints.Brands.ALL) {
            parameter("page", page)
            parameter("page_size", pageSize)
        }.body()
    }
    
    /**
     * Get a single brand by ID
     */
    suspend fun getBrandById(id: String): BrandDto {
        return httpClient.get(ApiEndpoints.Brands.byId(id)).body()
    }
    
    /**
     * Search brands
     */
    suspend fun searchBrands(query: String): List<BrandDto> {
        return httpClient.get(ApiEndpoints.Search.brands(query)).body()
    }
    
    /**
     * Get models for a brand
     */
    suspend fun getModelsByBrand(brandId: String): ModelsResponseDto {
        return httpClient.get(ApiEndpoints.Brands.models(brandId)).body()
    }
    
    /**
     * Get models with pagination
     */
    suspend fun getModelsByBrand(brandId: String, page: Int, pageSize: Int): ModelsResponseDto {
        return httpClient.get(ApiEndpoints.Brands.models(brandId)) {
            parameter("page", page)
            parameter("page_size", pageSize)
        }.body()
    }
    
    /**
     * Get a single model by ID
     */
    suspend fun getModelById(id: String): CarModelDto {
        return httpClient.get(ApiEndpoints.Models.byId(id)).body()
    }
    
    /**
     * Search models
     */
    suspend fun searchModels(query: String): List<CarModelDto> {
        return httpClient.get(ApiEndpoints.Search.models(query)).body()
    }
}
