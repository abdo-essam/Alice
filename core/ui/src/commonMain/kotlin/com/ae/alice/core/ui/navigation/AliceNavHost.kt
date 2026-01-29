package com.ae.alice.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute

/**
 * Main navigation host for Alice app.
 * Defines the navigation graph with all destinations.
 */
@Composable
fun AliceNavHost(
    navController: NavHostController,
    startDestination: Any = AliceRoutes.Home,
    homeScreen: @Composable () -> Unit,
    brandDetailsScreen: @Composable (brandId: String, brandName: String) -> Unit,
    modelDetailsScreen: @Composable (modelId: String) -> Unit,
    searchScreen: @Composable () -> Unit,
    favoritesScreen: @Composable () -> Unit,
    settingsScreen: @Composable () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Home - Brands List
        composable<AliceRoutes.Home> {
            homeScreen()
        }
        
        // Brand Details - Models List
        composable<AliceRoutes.BrandDetails> { backStackEntry ->
            val args: AliceRoutes.BrandDetails = backStackEntry.toRoute()
            brandDetailsScreen(args.brandId, args.brandName)
        }
        
        // Model Details
        composable<AliceRoutes.ModelDetails> { backStackEntry ->
            val args: AliceRoutes.ModelDetails = backStackEntry.toRoute()
            modelDetailsScreen(args.modelId)
        }
        
        // Search
        composable<AliceRoutes.Search> {
            searchScreen()
        }
        
        // Favorites
        composable<AliceRoutes.Favorites> {
            favoritesScreen()
        }
        
        // Settings
        composable<AliceRoutes.Settings> {
            settingsScreen()
        }
    }
}

/**
 * Navigation extensions
 */
fun NavHostController.navigateToBrandDetails(brandId: String, brandName: String) {
    navigate(AliceRoutes.BrandDetails(brandId, brandName))
}

fun NavHostController.navigateToModelDetails(modelId: String) {
    navigate(AliceRoutes.ModelDetails(modelId))
}

fun NavHostController.navigateToSearch() {
    navigate(AliceRoutes.Search)
}

fun NavHostController.navigateToFavorites() {
    navigate(AliceRoutes.Favorites)
}

fun NavHostController.navigateToSettings() {
    navigate(AliceRoutes.Settings)
}
