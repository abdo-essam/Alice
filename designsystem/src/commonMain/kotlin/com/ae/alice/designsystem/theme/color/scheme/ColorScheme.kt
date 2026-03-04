package com.ae.alice.designsystem.theme.color.scheme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Semantic color tokens used throughout the Alice UI.
 *
 * Components reference these roles (`brand.brand`, `primary.primary`, etc.)
 * instead of raw palette shades — this lets light/dark themes swap cleanly.
 */
@Immutable
data class ColorScheme(
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
    val info: Color,
    val shimmerBase: Color,
    val shimmerHighlight: Color
) {
    @Immutable
    data class Brand(
        val brand: Color,
        val brandVariant: Color,
        val onBrand: Color
    )

    @Immutable
    data class Primary(
        val primary: Color,
        val onPrimary: Color,
        val onPrimaryBody: Color,
        val onPrimaryHint: Color
    )

    @Immutable
    data class Secondary(
        val secondary: Color,
        val secondaryText: Color,
        val secondaryVariant: Color
    )

    @Immutable
    data class Border(
        val disabled: Color,
        val brand: Color,
        val error: Color,
        val success: Color
    )

    @Immutable
    data class Background(
        val surfaceLow: Color,
        val surface: Color,
        val surfaceHigh: Color,
        val bgError: Color,
        val bgWarning: Color,
        val bgSuccess: Color
    )
}

internal val LocalColorScheme = staticCompositionLocalOf { LightColorScheme }
