package com.ae.alice.core.domain.entity

/**
 * Domain entity representing a car model.
 */
data class CarModel(
    val id: String,
    val name: String,
    val brandId: String,
    val brandName: String,
    val imageUrl: String? = null,
    val year: Int? = null,
    val category: String? = null,
    val engineType: String? = null,
    val transmission: String? = null,
    val horsepower: Int? = null,
    val priceAmount: Double? = null,
    val priceCurrency: String = "USD",
    val description: String? = null,
    val isFavorite: Boolean = false
) {
    val formattedPrice: String?
        get() = priceAmount?.let { "$priceCurrency ${it.toLong()}" }
}
