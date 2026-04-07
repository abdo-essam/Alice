package com.ae.alice.navigation

import kotlinx.serialization.Serializable

object Routes {

    @Serializable
    data object Main

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
}
