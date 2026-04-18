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
import com.ae.alice.navigation.AppNavHost
import com.ae.alice.navigation.Routes
import org.koin.compose.KoinContext

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
                var startDestination by remember { mutableStateOf<Any?>(null) }

                LaunchedEffect(Unit) {
                    com.mmk.kmpauth.google.GoogleAuthProvider.create(
                        credentials = com.mmk.kmpauth.google.GoogleAuthCredentials(
                            serverId = BuildKonfig.WEB_CLIENT_ID
                        )
                    )
                    
                    startDestination = Routes.Login
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