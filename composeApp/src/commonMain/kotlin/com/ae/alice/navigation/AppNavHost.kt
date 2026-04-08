package com.ae.alice.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.ae.alice.presentation.screens.cardetails.CarDetailsScreen
import com.ae.alice.presentation.screens.main.MainScreen
import com.ae.alice.presentation.screens.models.ModelsScreen
import com.ae.alice.presentation.screens.places.PlacesScreen
@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Main
    ) {
        composable<Routes.Main> {
            MainScreen(
                onBrandClick = { brand ->
                    navController.navigate(Routes.Models(brand.id, brand.name))
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
                onBackClick = { navController.popBackStack() },
                onGetCarClick = { navController.navigate(Routes.Places) }
            )
        }

        composable<Routes.Places> { backStackEntry ->
            val savedStateHandle = backStackEntry.savedStateHandle
            val selectedCity = savedStateHandle.get<String>("selected_city")

            PlacesScreen(
                passedCity = selectedCity,
                onNavigateToDetails = {

                },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
