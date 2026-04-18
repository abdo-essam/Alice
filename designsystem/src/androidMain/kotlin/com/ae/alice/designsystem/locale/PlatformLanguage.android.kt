package com.ae.alice.designsystem.locale

import java.util.Locale

actual fun updatePlatformLanguage(languageCode: String) {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)
}
