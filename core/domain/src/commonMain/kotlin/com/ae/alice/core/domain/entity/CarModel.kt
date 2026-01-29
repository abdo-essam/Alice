package com.ae.alice.core.domain.entity

/**
 * Domain entity representing a car model.
 */
data class CarModel(
    val id: String,
    val name: String,
    val brandId: String,
    val brandName: String,
    val imageUrl: String?,
    val year: Int?,
    val category: CarCategory?,
    val engineType: EngineType?,
    val transmission: TransmissionType?,
    val horsepower: Int?,
    val price: Price?,
    val description: String?,
    val isFavorite: Boolean = false
)

/**
 * Car category enum
 */
enum class CarCategory {
    SEDAN,
    SUV,
    HATCHBACK,
    COUPE,
    CONVERTIBLE,
    WAGON,
    TRUCK,
    VAN,
    SPORTS,
    LUXURY,
    ELECTRIC,
    HYBRID,
    OTHER
}

/**
 * Engine type enum
 */
enum class EngineType {
    GASOLINE,
    DIESEL,
    ELECTRIC,
    HYBRID,
    PLUG_IN_HYBRID,
    HYDROGEN,
    OTHER
}

/**
 * Transmission type enum
 */
enum class TransmissionType {
    AUTOMATIC,
    MANUAL,
    CVT,
    DCT,
    OTHER
}

/**
 * Price value object
 */
data class Price(
    val amount: Double,
    val currency: String = "USD"
) {
    fun formatted(): String = "$currency ${String.format("%.0f", amount)}"
}

/**
 * Domain entity representing a list of car models with metadata
 */
data class CarModelsList(
    val models: List<CarModel>,
    val totalCount: Int,
    val page: Int = 1,
    val pageSize: Int = 20
)
