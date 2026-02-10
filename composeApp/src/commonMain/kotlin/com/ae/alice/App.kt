package com.ae.alice

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.ae.alice.designsystem.theme.ATheme
import com.ae.alice.navigation.AppNavHost

/**
 * Main App composable.
 */
@Composable
fun App() {
    ATheme {
        val navController = rememberNavController()
        AppNavHost(navController = navController)
    }
}