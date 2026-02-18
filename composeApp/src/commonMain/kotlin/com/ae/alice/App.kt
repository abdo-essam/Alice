package com.ae.alice

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.ae.alice.designsystem.theme.AliceTheme
import com.ae.alice.navigation.AppNavHost

/**
 * Main App composable.
 */
@Composable
fun App() {
    val settingsViewModel: com.ae.alice.presentation.screens.settings.SettingsViewModel = org.koin.compose.viewmodel.koinViewModel()
    val currentTheme by settingsViewModel.currentTheme.collectAsState()
    val currentLanguage by settingsViewModel.currentLanguage.collectAsState()

    AliceTheme(
        appTheme = currentTheme,
        language = currentLanguage
    ) {
        val navController = rememberNavController()
        AppNavHost(navController = navController)
    }
}