package com.ae.alice.navigation

import kotlinx.serialization.Serializable

/**
 * Type-safe navigation routes.
 */
object Routes {
    
    @Serializable
    data object Brands
    
    @Serializable
    data class Models(
        val brandId: String,
        val brandName: String
    )
    
    @Serializable
    data class CarDetails(
        val modelId: String,
        val modelName: String
    )
}
