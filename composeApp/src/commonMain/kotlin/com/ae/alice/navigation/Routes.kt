package com.ae.alice.navigation

import kotlinx.serialization.Serializable

/**
 * Type-safe navigation routes.
 */
object Routes {

    /** Main screen with bottom navigation (Home, Archive, Profile) */
    @Serializable
    data object Main

    /** Brands listing (now embedded in Main as Home tab) */
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

    @Serializable
    data object Places

    @Serializable
    data object PickLocation

    @Serializable
    data object Profile
}
