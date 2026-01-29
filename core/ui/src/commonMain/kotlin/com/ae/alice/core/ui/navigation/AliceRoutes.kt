package com.ae.alice.core.ui.navigation

import kotlinx.serialization.Serializable

/**
 * Type-safe navigation routes for Alice app.
 * Using Kotlin Serialization for type-safe navigation.
 */
object AliceRoutes {
    
    /**
     * Home screen showing all brands
     */
    @Serializable
    data object Home
    
    /**
     * Brand details showing models
     */
    @Serializable
    data class BrandDetails(
        val brandId: String,
        val brandName: String
    )
    
    /**
     * Model details screen
     */
    @Serializable
    data class ModelDetails(
        val modelId: String
    )
    
    /**
     * Search screen
     */
    @Serializable
    data object Search
    
    /**
     * Favorites screen
     */
    @Serializable
    data object Favorites
    
    /**
     * Settings screen
     */
    @Serializable
    data object Settings
}

/**
 * Bottom navigation destinations
 */
enum class BottomNavDestination(
    val route: Any,
    val labelKey: String,
    val iconName: String
) {
    HOME(
        route = AliceRoutes.Home,
        labelKey = "nav_home",
        iconName = "home"
    ),
    SEARCH(
        route = AliceRoutes.Search,
        labelKey = "nav_search",
        iconName = "search"
    ),
    FAVORITES(
        route = AliceRoutes.Favorites,
        labelKey = "nav_favorites",
        iconName = "favorite"
    ),
    SETTINGS(
        route = AliceRoutes.Settings,
        labelKey = "nav_settings",
        iconName = "settings"
    )
}
