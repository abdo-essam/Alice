package com.ae.alice

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.ae.alice.designsystem.locale.AppLocaleProvider
import com.ae.alice.designsystem.locale.LocaleState
import com.ae.alice.designsystem.theme.AliceTheme
import com.ae.alice.navigation.AppNavHost
import org.koin.compose.KoinContext

@Composable
fun App() {
    val localeState = remember { LocaleState() }

    KoinContext {
        AppLocaleProvider(localeState = localeState) {
            AliceTheme(language = localeState.language.code) {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}