package com.ae.alice.designsystem.locale

import androidx.compose.runtime.Composable

@Composable
actual fun PlatformLocaleEffect(languageCode: String) {
    // No-op for iOS since NSUserDefaults was already updated in updatePlatformLanguage
}
