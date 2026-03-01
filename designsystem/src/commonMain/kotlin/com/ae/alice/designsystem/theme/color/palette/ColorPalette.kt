package com.ae.alice.designsystem.theme.color.palette

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Raw color palette for the Alice design system.
 * Each color family (copper, brown, gray, etc.) provides a 10-shade scale
 * (50–900). Components never reference this directly — they use [ColorScheme]
 * semantic tokens instead.
 */
@Immutable
data class ColorPalette(
    val copper: ColorScale,
    val brown: ColorScale,
    val gray: ColorScale,
    val red: ColorScale,
    val yellow: ColorScale,
    val green: ColorScale,
    val blue: ColorScale
) {
    @Immutable
    data class ColorScale(
        val shade50: Color,
        val shade100: Color,
        val shade200: Color,
        val shade300: Color,
        val shade400: Color,
        val shade500: Color,
        val shade600: Color,
        val shade700: Color,
        val shade800: Color,
        val shade900: Color
    )
}
