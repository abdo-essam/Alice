package com.ae.alice.domain.entity

/**
 * Domain entity representing a car brand/manufacturer.
 */
data class Brand(
    val id: String,
    val name: String,
    val logoUrl: String? = null,
    val country: String? = null,
    val foundedYear: Int? = null,
    val description: String? = null,
    val modelsCount: Int = 0
)
