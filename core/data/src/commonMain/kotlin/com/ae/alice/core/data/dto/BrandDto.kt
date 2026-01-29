package com.ae.alice.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data Transfer Object for Brand from API
 */
@Serializable
data class BrandDto(
    @SerialName("id")
    val id: String,
    
    @SerialName("name")
    val name: String,
    
    @SerialName("logo_url")
    val logoUrl: String? = null,
    
    @SerialName("country")
    val country: String? = null,
    
    @SerialName("founded_year")
    val foundedYear: Int? = null,
    
    @SerialName("description")
    val description: String? = null,
    
    @SerialName("models_count")
    val modelsCount: Int = 0
)

/**
 * Response wrapper for brands list
 */
@Serializable
data class BrandsResponseDto(
    @SerialName("data")
    val data: List<BrandDto>,
    
    @SerialName("total_count")
    val totalCount: Int,
    
    @SerialName("page")
    val page: Int = 1,
    
    @SerialName("page_size")
    val pageSize: Int = 20
)
