package com.ae.alice.designsystem.theme.typography

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

/**
 * Typography tokens grouped by semantic role.
 *
 * Each group exposes large / medium / small variants.
 * Concrete [TextStyle] values are created by [createThemeTypography] which
 * picks the correct font family based on the current language.
 */
@Immutable
data class Typography(
    val headline: Headline,
    val title: Title,
    val body: Body,
    val label: Label
) {
    @Immutable
    data class Headline(
        val large: TextStyle,
        val medium: TextStyle,
        val small: TextStyle
    )

    @Immutable
    data class Title(
        val large: TextStyle,
        val medium: TextStyle,
        val small: TextStyle
    )

    @Immutable
    data class Body(
        val large: TextStyle,
        val medium: TextStyle,
        val small: TextStyle
    )

    @Immutable
    data class Label(
        val large: TextStyle,
        val medium: TextStyle,
        val small: TextStyle
    )
}

internal val LocalTypography =
    staticCompositionLocalOf<Typography> { error("No Typography provided") }
