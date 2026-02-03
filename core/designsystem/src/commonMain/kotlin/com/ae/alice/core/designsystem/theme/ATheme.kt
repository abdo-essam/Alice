package com.ae.alice.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Custom colors for extended theming.
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

private val LightColorScheme: ColorScheme = lightColorScheme(
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

private val DarkColorScheme: ColorScheme = darkColorScheme(
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

private val Shapes = Shapes(
    extraSmall = RoundedCornerShape(ADimensions.RadiusXs),
    small = RoundedCornerShape(ADimensions.RadiusSm),
    medium = RoundedCornerShape(ADimensions.RadiusMd),
    large = RoundedCornerShape(ADimensions.RadiusLg),
    extraLarge = RoundedCornerShape(ADimensions.RadiusXl)
)

/**
 * Alice app theme.
 */
@Composable
fun ATheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val extendedColors = if (darkTheme) DarkExtendedColors else LightExtendedColors
    
    CompositionLocalProvider(LocalExtendedColors provides extendedColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = ATypography.Material3Typography,
            shapes = Shapes,
            content = content
        )
    }
}

/**
 * Access extended colors.
 */
object ATheme {
    val extendedColors: ExtendedColors
        @Composable
        get() = LocalExtendedColors.current
}
