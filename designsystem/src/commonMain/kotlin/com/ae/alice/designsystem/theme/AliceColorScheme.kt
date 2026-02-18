package com.ae.alice.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * MENA-style semantic color scheme.
 * Groups colors by purpose for intuitive access: Theme.colorScheme.brand.brand
 */
@Immutable
data class AliceColorScheme(
    val brand: Brand,
    val primary: Primary,
    val secondary: Secondary,
    val border: Border,
    val background: Background,
    val shadePrimary: Color,
    val shadeSecondary: Color,
    val shadeTertiary: Color,
    val stroke: Color,
    val textDisabled: Color,
    val disabled: Color,
    val error: Color,
    val warning: Color,
    val success: Color,
) {
    data class Brand(
        val brand: Color,
        val brandVariant: Color,
        val onBrand: Color
    )

    data class Primary(
        val primary: Color,
        val onPrimary: Color,
        val onPrimaryBody: Color,
        val onPrimaryHint: Color
    )

    data class Secondary(
        val secondary: Color,
        val secondaryText: Color,
        val secondaryVariant: Color
    )

    data class Border(
        val disabled: Color,
        val brand: Color,
        val error: Color,
        val success: Color
    )

    data class Background(
        val surfaceLow: Color,
        val surface: Color,
        val surfaceHigh: Color,
        val bgError: Color,
        val bgWarning: Color,
        val bgSuccess: Color
    )
}

// region — Light scheme using Alice brand colors
val LightAliceColorScheme = AliceColorScheme(
    brand = AliceColorScheme.Brand(
        brand = AColors.Primary,            // Copper/Bronze
        brandVariant = AColors.PrimaryLight,
        onBrand = AColors.OnPrimary
    ),
    primary = AliceColorScheme.Primary(
        primary = AColors.Secondary,        // Dark Brown
        onPrimary = AColors.OnSecondary,
        onPrimaryBody = AColors.OnSecondary.copy(alpha = 0.6f),
        onPrimaryHint = AColors.OnSecondary.copy(alpha = 0.38f)
    ),
    secondary = AliceColorScheme.Secondary(
        secondary = AColors.Secondary,
        secondaryText = AColors.SecondaryLight,
        secondaryVariant = AColors.AccentLight
    ),
    border = AliceColorScheme.Border(
        disabled = AColors.BorderLight,
        brand = AColors.Primary,
        error = AColors.Error,
        success = AColors.Success
    ),
    background = AliceColorScheme.Background(
        surfaceLow = AliceLightColors.Background,
        surface = AliceLightColors.Surface,
        surfaceHigh = AliceLightColors.SurfaceVariant,
        bgError = Color(0xFFFEE4E2),
        bgWarning = Color(0xFFFFFAEB),
        bgSuccess = Color(0xFFE6F6EA)
    ),
    shadePrimary = AliceLightColors.TextPrimary,
    shadeSecondary = AliceLightColors.TextSecondary,
    shadeTertiary = AliceLightColors.TextHint,
    stroke = AColors.BorderLight,
    textDisabled = AliceLightColors.TextDisabled,
    disabled = AColors.BorderLight,
    error = AColors.Error,
    warning = AColors.Warning,
    success = AColors.Success
)
// endregion

// region — Dark scheme
val DarkAliceColorScheme = AliceColorScheme(
    brand = AliceColorScheme.Brand(
        brand = AColors.PrimaryLight,
        brandVariant = AColors.PrimaryDark,
        onBrand = AColors.OnPrimary
    ),
    primary = AliceColorScheme.Primary(
        primary = Color(0xFFFFFFFF),
        onPrimary = AliceDarkColors.TextPrimary,
        onPrimaryBody = AliceDarkColors.TextSecondary,
        onPrimaryHint = AliceDarkColors.TextHint
    ),
    secondary = AliceColorScheme.Secondary(
        secondary = AColors.SecondaryLight,
        secondaryText = AColors.SecondaryLight,
        secondaryVariant = AColors.AccentLight
    ),
    border = AliceColorScheme.Border(
        disabled = AColors.BorderDark,
        brand = AColors.PrimaryLight,
        error = Color(0xFFF97066),
        success = Color(0xFF4EBF6D)
    ),
    background = AliceColorScheme.Background(
        surfaceLow = AliceDarkColors.Background,
        surface = AliceDarkColors.Surface,
        surfaceHigh = AliceDarkColors.SurfaceVariant,
        bgError = Color(0xFF7A271A),
        bgWarning = Color(0xFF7A2E0E),
        bgSuccess = Color(0xFF00621F)
    ),
    shadePrimary = AliceDarkColors.TextPrimary,
    shadeSecondary = AliceDarkColors.TextSecondary,
    shadeTertiary = AliceDarkColors.TextHint,
    stroke = AColors.BorderDark,
    textDisabled = AliceDarkColors.TextDisabled,
    disabled = AColors.BorderDark,
    error = Color(0xFFF97066),
    warning = Color(0xFFFEC84B),
    success = Color(0xFF4EBF6D)
)
// endregion

internal val LocalAliceColorScheme = staticCompositionLocalOf { LightAliceColorScheme }
