package com.ae.alice.designsystem.locale

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

/**
 * Supported languages.
 */
enum class AppLanguage(val code: String, val displayName: String) {
    ENGLISH("en", "English"),
    ARABIC("ar", "العربية");

    val isRtl: Boolean get() = this == ARABIC
}

/**
 * Manages the current locale state.
 */
class LocaleState {
    var language by mutableStateOf(AppLanguage.ENGLISH)
        internal set

    fun switchLanguage() {
        language = when (language) {
            AppLanguage.ENGLISH -> AppLanguage.ARABIC
            AppLanguage.ARABIC -> AppLanguage.ENGLISH
        }
    }

    fun setLanguage(lang: AppLanguage) {
        language = lang
    }
}

/**
 * CompositionLocal providing locale state throughout the tree.
 */
val LocalAppLocale = compositionLocalOf { LocaleState() }

/**
 * Provider that wraps content with locale-aware layout direction.
 */
@Composable
fun AppLocaleProvider(
    localeState: LocaleState = remember { LocaleState() },
    content: @Composable () -> Unit
) {
    val layoutDirection = if (localeState.language.isRtl) {
        LayoutDirection.Rtl
    } else {
        LayoutDirection.Ltr
    }

    CompositionLocalProvider(
        LocalAppLocale provides localeState,
        LocalLayoutDirection provides layoutDirection
    ) {
        content()
    }
}
