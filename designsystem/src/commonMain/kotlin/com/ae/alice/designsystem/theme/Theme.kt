package com.ae.alice.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.ae.alice.designsystem.locale.AppLanguage
import com.ae.alice.designsystem.theme.color.scheme.ColorScheme
import com.ae.alice.designsystem.theme.color.scheme.DarkColorScheme
import com.ae.alice.designsystem.theme.color.scheme.LightColorScheme
import com.ae.alice.designsystem.theme.color.scheme.LocalColorScheme
import com.ae.alice.designsystem.theme.radius.AliceRadius
import com.ae.alice.designsystem.theme.radius.LocalRadius
import com.ae.alice.designsystem.theme.radius.Radius
import com.ae.alice.designsystem.theme.spacing.AliceSpacing
import com.ae.alice.designsystem.theme.spacing.LocalSpacing
import com.ae.alice.designsystem.theme.spacing.Spacing
import com.ae.alice.designsystem.theme.typography.LocalTypography
import com.ae.alice.designsystem.theme.typography.Typography
import com.ae.alice.designsystem.theme.typography.createThemeTypography
import com.ae.alice.designsystem.util.AppTheme

/**
 * Root composable that provides the full Alice design-system token set.
 *
 * All tokens are delivered via [CompositionLocalProvider], so every child
 * can access them through [Theme] — no static singletons required.
 *
 * @param language ISO 639-1 code (e.g. "en", "ar") — controls font family
 *                 and layout direction.
 * @param appTheme one of [AppTheme] names — controls light / dark / system.
 */
@Composable
fun AliceTheme(
    language: String = AppLanguage.ENGLISH.code,
    appTheme: String = AppTheme.SYSTEM.name,
    isSystemDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = remember(appTheme, isSystemDark) {
        when (appTheme) {
            AppTheme.LIGHT.name -> LightColorScheme
            AppTheme.DARK.name -> DarkColorScheme
            AppTheme.SYSTEM.name -> if (isSystemDark) DarkColorScheme else LightColorScheme
            else -> LightColorScheme
        }
    }

    val typography = createThemeTypography(language)

    val layoutDirection = remember(language) {
        if (language == AppLanguage.ARABIC.code) LayoutDirection.Rtl else LayoutDirection.Ltr
    }

    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalSpacing provides AliceSpacing,
        LocalRadius provides AliceRadius,
        LocalTypography provides typography,
        LocalLayoutDirection provides layoutDirection
    ) {
        content()
    }
}

/**
 * Single entry-point for reading design-system tokens in any composable.
 *
 * Usage:
 * ```
 * Text(
 *     text = "Hello",
 *     style = Theme.typography.title.medium,
 *     color = Theme.colorScheme.shadePrimary
 * )
 * Spacer(Modifier.height(Theme.spacing._16))
 * ```
 */
object Theme {
    val colorScheme: ColorScheme
        @Composable @ReadOnlyComposable get() = LocalColorScheme.current

    val typography: Typography
        @Composable @ReadOnlyComposable get() = LocalTypography.current

    val spacing: Spacing
        @Composable @ReadOnlyComposable get() = LocalSpacing.current

    val radius: Radius
        @Composable @ReadOnlyComposable get() = LocalRadius.current
}
