package com.ae.alice.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.ae.alice.screens.brands.BrandsScreen
import com.ae.alice.screens.models.ModelsScreen

/**
 * App navigation host.
 */
@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Brands
    ) {
        composable<Routes.Brands> {
            BrandsScreen(
                onBrandClick = { brandId, brandName ->
                    navController.navigate(Routes.Models(brandId, brandName))
                }
            )
        }
        
        composable<Routes.Models> { backStackEntry ->
            val args: Routes.Models = backStackEntry.toRoute()
            ModelsScreen(
                brandId = args.brandId,
                brandName = args.brandName,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
