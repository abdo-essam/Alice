package com.ae.alice

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.ae.alice.designsystem.locale.AppLocaleProvider
import com.ae.alice.designsystem.locale.LocaleState
import com.ae.alice.designsystem.theme.AliceTheme
import com.ae.alice.navigation.AppNavHost

/**
 * Main App composable.
 */
@Composable
fun App() {
    val localeState = remember { LocaleState() }

    AppLocaleProvider(localeState = localeState) {
        AliceTheme {
            val navController = rememberNavController()
            AppNavHost(navController = navController)
        }
    }
}