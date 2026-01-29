package com.ae.alice.core.domain.entity

/**
 * Domain entity representing a car brand/manufacturer.
 */
data class Brand(
    val id: String,
    val name: String,
    val logoUrl: String?,
    val country: String?,
    val foundedYear: Int?,
    val description: String?,
    val modelsCount: Int = 0
)

/**
 * Domain entity representing a list of brands with metadata
 */
data class BrandsList(
    val brands: List<Brand>,
    val totalCount: Int,
    val page: Int = 1,
    val pageSize: Int = 20
)
