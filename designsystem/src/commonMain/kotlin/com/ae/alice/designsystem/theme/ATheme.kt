package com.ae.alice.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import com.ae.alice.domain.model.AppLanguage
import com.ae.alice.domain.model.AppTheme

/**
 * Extended colors for components that need shimmer/card/divider tokens.
 */
data class ExtendedColors(
    val success: androidx.compose.ui.graphics.Color,
    val warning: androidx.compose.ui.graphics.Color,
    val cardBackground: androidx.compose.ui.graphics.Color,
    val divider: androidx.compose.ui.graphics.Color,
    val shimmerBase: androidx.compose.ui.graphics.Color,
    val shimmerHighlight: androidx.compose.ui.graphics.Color
)

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        success = AColors.Success,
        warning = AColors.Warning,
        cardBackground = AColors.Light.Card,
        divider = AColors.Light.Divider,
        shimmerBase = AColors.ShimmerBase,
        shimmerHighlight = AColors.ShimmerHighlight
    )
}

// Material3 color schemes — fed into MaterialTheme for M3 component compatibility
private val M3LightColorScheme = lightColorScheme(
    primary = AColors.Primary,
    onPrimary = AColors.OnPrimary,
    primaryContainer = AColors.PrimaryLight,
    secondary = AColors.Secondary,
    onSecondary = AColors.OnSecondary,
    background = AColors.Light.Background,
    onBackground = AColors.Light.TextPrimary,
    surface = AColors.Light.Surface,
    onSurface = AColors.Light.TextPrimary,
    surfaceVariant = AColors.Light.SurfaceVariant,
    error = AColors.Error,
    outline = AColors.BorderLight
)

private val M3DarkColorScheme = darkColorScheme(
    primary = AColors.Primary,
    onPrimary = AColors.OnPrimary,
    primaryContainer = AColors.PrimaryDark,
    secondary = AColors.SecondaryLight,
    onSecondary = AColors.OnSecondary,
    background = AColors.Dark.Background,
    onBackground = AColors.Dark.TextPrimary,
    surface = AColors.Dark.Surface,
    onSurface = AColors.Dark.TextPrimary,
    surfaceVariant = AColors.Dark.SurfaceVariant,
    error = AColors.Error,
    outline = AColors.BorderDark
)

private val LightExtendedColors = ExtendedColors(
    success = AColors.Success,
    warning = AColors.Warning,
    cardBackground = AColors.Light.Card,
    divider = AColors.Light.Divider,
    shimmerBase = AColors.ShimmerBase,
    shimmerHighlight = AColors.ShimmerHighlight
)

private val DarkExtendedColors = ExtendedColors(
    success = AColors.Success,
    warning = AColors.Warning,
    cardBackground = AColors.Dark.Card,
    divider = AColors.Dark.Divider,
    shimmerBase = AColors.ShimmerBaseDark,
    shimmerHighlight = AColors.ShimmerHighlightDark
)

private val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(ADimensions.RadiusXs),
    small = RoundedCornerShape(ADimensions.RadiusSm),
    medium = RoundedCornerShape(ADimensions.RadiusMd),
    large = RoundedCornerShape(ADimensions.RadiusLg),
    extraLarge = RoundedCornerShape(ADimensions.RadiusXl)
)

/**
 * Alice app theme — MENA-style CompositionLocal providers.
 *
 * Provides:
 *  - [LocalAliceColorScheme] → ATheme.colorScheme
 *  - [LocalAliceTypography] → ATheme.typo
 *  - [LocalAliceDimensions] → ATheme.dimens
 *  - [LocalExtendedColors] → ATheme.extendedColors
 *  - MaterialTheme for M3 component compat
 */
@Composable
fun AliceTheme(
    appTheme: AppTheme = AppTheme.SYSTEM,
    language: AppLanguage = AppLanguage.English,
    systemIsDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val isDarkTheme = when (appTheme) {
        AppTheme.LIGHT -> false
        AppTheme.DARK -> true
        AppTheme.SYSTEM -> systemIsDark
    }

    val layoutDirection = when (language) {
        AppLanguage.Arabic -> androidx.compose.ui.unit.LayoutDirection.Rtl
        else -> androidx.compose.ui.unit.LayoutDirection.Ltr
    }

    val m3ColorScheme = if (isDarkTheme) M3DarkColorScheme else M3LightColorScheme
    val extendedColors = if (isDarkTheme) DarkExtendedColors else LightExtendedColors
    val aliceColorScheme = if (isDarkTheme) DarkAliceColorScheme else LightAliceColorScheme
    val aliceTypography = createAliceTypography(brandColor = aliceColorScheme.brand.brand)

    CompositionLocalProvider(
        LocalAliceColorScheme provides aliceColorScheme,
        LocalAliceTypography provides aliceTypography,
        LocalAliceDimensions provides AliceDimensionDefaults,
        LocalExtendedColors provides extendedColors,
        androidx.compose.ui.platform.LocalLayoutDirection provides layoutDirection
    ) {
        MaterialTheme(
            colorScheme = m3ColorScheme,
            typography = ATypography.Material3Typography,
            shapes = AppShapes,
            content = content
        )
    }
}

/**
 * Central design token accessor — MENA-style.
 *
 * Usage:
 * ```
 *   ATheme.colorScheme.brand.brand
 *   ATheme.colorScheme.background.surface
 *   ATheme.typo.title.medium
 *   ATheme.dimens.spacingMd
 * ```
 */
object ATheme {
    /** Semantic color scheme — reactive to dark/light mode */
    val colorScheme: AliceColorScheme
        @Composable @ReadOnlyComposable get() = LocalAliceColorScheme.current

    /** Grouped typography using Poppins fonts */
    val typo: AliceTypography
        @Composable @ReadOnlyComposable get() = LocalAliceTypography.current

    /** Dimensions and spacing */
    val dimens: AliceDimensions
        @Composable @ReadOnlyComposable get() = LocalAliceDimensions.current

    /** Extended colors (shimmer, card background, divider) */
    val extendedColors: ExtendedColors
        @Composable @ReadOnlyComposable get() = LocalExtendedColors.current

    // ── Backward-compatible accessors (deprecated) ──────────────
    // These keep existing component code compiling while we migrate.

    @Deprecated("Use ATheme.colorScheme instead", ReplaceWith("ATheme.colorScheme"))
    val colors: AColors = AColors

    @Deprecated("Use ATheme.typo instead", ReplaceWith("ATheme.typo"))
    val typography: ATypography = ATypography
}
