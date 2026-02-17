package com.ae.alice

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.ae.alice.designsystem.theme.AliceTheme
import com.ae.alice.navigation.AppNavHost

/**
 * Main App composable.
 */
@Composable
fun App() {
    AliceTheme {
        val navController = rememberNavController()
        AppNavHost(navController = navController)
    }
}