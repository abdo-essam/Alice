package com.ae.alice.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.ae.alice.designsystem.components.scaffold.Scaffold
import com.ae.alice.presentation.screens.auth.login.LoginScreen
import com.ae.alice.presentation.screens.auth.register.RegisterScreen
import com.ae.alice.presentation.screens.cardetails.CarDetailsScreen
import com.ae.alice.presentation.screens.main.MainScreen
import com.ae.alice.presentation.screens.main.components.MainBottomNavBar
import com.ae.alice.presentation.screens.models.ModelsScreen
import com.ae.alice.presentation.screens.places.PlacesScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Any = Routes.Login,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable<Routes.Login> {
            LoginScreen(
                onNavigateToMain = {
                    navController.navigate(Routes.Main) {
                        popUpTo(Routes.Login) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Routes.Register)
                }
            )
        }

        composable<Routes.Register> {
            RegisterScreen(
                onNavigateToMain = {
                    navController.navigate(Routes.Main) {
                        popUpTo(Routes.Login) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable<Routes.Main> {
            Scaffold(
                bottomBar = {
                    MainBottomNavBar(
                        selectedTab = selectedTab,
                        onTabSelected = { newTab ->
                            selectedTab = newTab
                            navController.popBackStack(Routes.Main, inclusive = false)
                        }
                    )
                }
            ) {
                MainScreen(
                    selectedTab = selectedTab,
                    onBrandClick = { brand ->
                        navController.navigate(Routes.Models(brand.id, brand.name))
                    },
                    onLogout = {
                        navController.navigate(Routes.Login) {
                            popUpTo(Routes.Main) { inclusive = true }
                        }
                    }
                )
            }
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
            val selectedCity = backStackEntry.savedStateHandle.get<String>("selected_city")
            PlacesScreen(
                passedCity = selectedCity,
                onNavigateToDetails = {},
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
