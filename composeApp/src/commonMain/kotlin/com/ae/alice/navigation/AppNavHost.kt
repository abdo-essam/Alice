package com.ae.alice.navigation
import com.ae.alice.presentation.screens.settings.SettingsScreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.ae.alice.presentation.screens.brands.BrandsScreen
import com.ae.alice.presentation.screens.cardetails.CarDetailsScreen
import com.ae.alice.presentation.screens.models.ModelsScreen

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
                onBrandClick = { brand ->
                    navController.navigate(Routes.Models(brand.id, brand.name))
                },
                onSettingsClick = {
                    navController.navigate(Routes.Settings)
                }
            )
        }
        
        composable<Routes.Models> { backStackEntry ->
            val args: Routes.Models = backStackEntry.toRoute()
            ModelsScreen(
                brandId = args.brandId,
                brandName = args.brandName,
                onBackClick = { navController.popBackStack() },
                onModelClick = { model ->
                    navController.navigate(Routes.CarDetails(model.id, model.name))
                }
            )
        }
        
        composable<Routes.CarDetails> { backStackEntry ->
            val args: Routes.CarDetails = backStackEntry.toRoute()
            CarDetailsScreen(
                modelId = args.modelId,
                modelName = args.modelName,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable<Routes.Settings> {
            com.ae.alice.presentation.screens.settings.SettingsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
