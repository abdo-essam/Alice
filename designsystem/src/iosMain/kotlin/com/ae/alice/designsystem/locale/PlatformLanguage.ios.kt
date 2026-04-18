package com.ae.alice.designsystem.locale

import platform.Foundation.NSUserDefaults

actual fun updatePlatformLanguage(languageCode: String) {
    NSUserDefaults.standardUserDefaults.setObject(listOf(languageCode), "AppleLanguages")
}
