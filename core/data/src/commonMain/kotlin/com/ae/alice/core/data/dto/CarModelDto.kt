package com.ae.alice.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data Transfer Object for Car Model from API
 */
@Serializable
data class CarModelDto(
    @SerialName("id")
    val id: String,
    
    @SerialName("name")
    val name: String,
    
    @SerialName("brand_id")
    val brandId: String,
    
    @SerialName("brand_name")
    val brandName: String,
    
    @SerialName("image_url")
    val imageUrl: String? = null,
    
    @SerialName("year")
    val year: Int? = null,
    
    @SerialName("category")
    val category: String? = null,
    
    @SerialName("engine_type")
    val engineType: String? = null,
    
    @SerialName("transmission")
    val transmission: String? = null,
    
    @SerialName("horsepower")
    val horsepower: Int? = null,
    
    @SerialName("price")
    val price: Double? = null,
    
    @SerialName("currency")
    val currency: String = "USD",
    
    @SerialName("description")
    val description: String? = null
)

/**
 * Response wrapper for models list
 */
@Serializable
data class ModelsResponseDto(
    @SerialName("data")
    val data: List<CarModelDto>,
    
    @SerialName("total_count")
    val totalCount: Int,
    
    @SerialName("page")
    val page: Int = 1,
    
    @SerialName("page_size")
    val pageSize: Int = 20
)
