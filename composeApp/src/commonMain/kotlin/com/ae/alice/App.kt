package com.ae.alice

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import coil3.request.crossfade
import com.ae.alice.designsystem.locale.AppLocaleProvider
import com.ae.alice.designsystem.locale.LocaleState
import com.ae.alice.designsystem.theme.AliceTheme
import com.ae.alice.domain.repository.AuthRepository
import com.ae.alice.navigation.AppNavHost
import com.ae.alice.navigation.Routes
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

@Composable
fun App() {
    val localeState = remember { LocaleState() }

    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .components {
                add(KtorNetworkFetcherFactory())
            }
            .crossfade(true)
            .build()
    }

    KoinContext {
        AppLocaleProvider(localeState = localeState) {
            AliceTheme(language = localeState.language.code) {
                val navController = rememberNavController()
                val authRepository = koinInject<AuthRepository>()
                var startDestination by remember { mutableStateOf<Any?>(null) }

                LaunchedEffect(Unit) {
                    val user = authRepository.getCurrentUser()
                    startDestination = if (user != null) Routes.Main else Routes.Login
                }

                startDestination?.let { dest ->
                    AppNavHost(
                        navController = navController,
                        startDestination = dest
                    )
                }
            }
        }
    }
}