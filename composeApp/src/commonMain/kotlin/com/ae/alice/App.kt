package com.ae.alice

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ae.alice.core.designsystem.theme.AliceTheme
import com.ae.alice.core.ui.navigation.AliceNavHost

/**
 * Main App composable - entry point for the Alice app
 */
@Composable
fun App() {
    AliceTheme {
        val navController = rememberNavController()
        
        Scaffold { paddingValues ->
            AliceNavHost(
                navController = navController,
                homeScreen = {
                    PlaceholderScreen("Home - Brands")
                },
                brandDetailsScreen = { brandId, brandName ->
                    PlaceholderScreen("Brand Details: $brandName")
                },
                modelDetailsScreen = { modelId ->
                    PlaceholderScreen("Model Details: $modelId")
                },
                searchScreen = {
                    PlaceholderScreen("Search")
                },
                favoritesScreen = {
                    PlaceholderScreen("Favorites")
                },
                settingsScreen = {
                    PlaceholderScreen("Settings")
                }
            )
        }
    }
}

/**
 * Temporary placeholder screen for development
 */
@Composable
private fun PlaceholderScreen(title: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = title)
    }
}