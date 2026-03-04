package com.ae.alice.domain.entity

/**
 * Domain entity representing a place/service provider.
 */
data class Place(
    val id: String,
    val name: String,
    val address: String,
    val categoryId: String,
    val imageUrl: String? = null,
    val isSaved: Boolean = false,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)
