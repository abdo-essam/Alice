package com.ae.alice.designsystem.locale

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

@Composable
actual fun PlatformLocaleEffect(languageCode: String) {
    val context = LocalContext.current
    LaunchedEffect(languageCode) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = context.resources.configuration
        config.setLocale(locale)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }
}
