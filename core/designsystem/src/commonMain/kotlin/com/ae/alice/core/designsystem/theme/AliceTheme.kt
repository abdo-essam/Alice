package com.ae.alice.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

/**
 * Light color scheme for Alice app
 */
private val LightColorScheme = lightColorScheme(
    primary = AliceColors.Primary,
    onPrimary = AliceColors.SurfaceLight,
    primaryContainer = AliceColors.PrimaryLight,
    onPrimaryContainer = AliceColors.SurfaceLight,
    secondary = AliceColors.Secondary,
    onSecondary = AliceColors.SurfaceLight,
    secondaryContainer = AliceColors.SecondaryLight,
    onSecondaryContainer = AliceColors.SurfaceLight,
    tertiary = AliceColors.Tertiary,
    onTertiary = AliceColors.SurfaceLight,
    tertiaryContainer = AliceColors.TertiaryLight,
    onTertiaryContainer = AliceColors.SurfaceDark,
    error = AliceColors.Error,
    onError = AliceColors.SurfaceLight,
    errorContainer = AliceColors.ErrorLight,
    onErrorContainer = AliceColors.SurfaceDark,
    background = AliceColors.BackgroundLight,
    onBackground = AliceColors.OnBackgroundLight,
    surface = AliceColors.SurfaceLight,
    onSurface = AliceColors.OnSurfaceLight,
    surfaceVariant = AliceColors.SurfaceVariantLight,
    onSurfaceVariant = AliceColors.TextSecondaryLight,
    outline = AliceColors.BorderLight,
    outlineVariant = AliceColors.DividerLight
)

/**
 * Dark color scheme for Alice app
 */
private val DarkColorScheme = darkColorScheme(
    primary = AliceColors.PrimaryLight,
    onPrimary = AliceColors.SurfaceDark,
    primaryContainer = AliceColors.Primary,
    onPrimaryContainer = AliceColors.SurfaceLight,
    secondary = AliceColors.SecondaryLight,
    onSecondary = AliceColors.SurfaceDark,
    secondaryContainer = AliceColors.Secondary,
    onSecondaryContainer = AliceColors.SurfaceLight,
    tertiary = AliceColors.TertiaryLight,
    onTertiary = AliceColors.SurfaceDark,
    tertiaryContainer = AliceColors.Tertiary,
    onTertiaryContainer = AliceColors.SurfaceLight,
    error = AliceColors.ErrorLight,
    onError = AliceColors.SurfaceDark,
    errorContainer = AliceColors.Error,
    onErrorContainer = AliceColors.SurfaceLight,
    background = AliceColors.BackgroundDark,
    onBackground = AliceColors.OnBackgroundDark,
    surface = AliceColors.SurfaceDark,
    onSurface = AliceColors.OnSurfaceDark,
    surfaceVariant = AliceColors.SurfaceVariantDark,
    onSurfaceVariant = AliceColors.TextSecondaryDark,
    outline = AliceColors.BorderDark,
    outlineVariant = AliceColors.DividerDark
)

/**
 * Material3 Typography using Alice typography styles
 */
private val AliceMaterialTypography = Typography(
    displayLarge = AliceTypography.DisplayLarge,
    displayMedium = AliceTypography.DisplayMedium,
    displaySmall = AliceTypography.DisplaySmall,
    headlineLarge = AliceTypography.HeadlineLarge,
    headlineMedium = AliceTypography.HeadlineMedium,
    headlineSmall = AliceTypography.HeadlineSmall,
    titleLarge = AliceTypography.TitleLarge,
    titleMedium = AliceTypography.TitleMedium,
    titleSmall = AliceTypography.TitleSmall,
    bodyLarge = AliceTypography.BodyLarge,
    bodyMedium = AliceTypography.BodyMedium,
    bodySmall = AliceTypography.BodySmall,
    labelLarge = AliceTypography.LabelLarge,
    labelMedium = AliceTypography.LabelMedium,
    labelSmall = AliceTypography.LabelSmall
)

/**
 * Material3 Shapes using Alice dimensions
 */
private val AliceShapes = Shapes(
    extraSmall = RoundedCornerShape(AliceDimensions.RadiusXs),
    small = RoundedCornerShape(AliceDimensions.RadiusSm),
    medium = RoundedCornerShape(AliceDimensions.RadiusMd),
    large = RoundedCornerShape(AliceDimensions.RadiusLg),
    extraLarge = RoundedCornerShape(AliceDimensions.RadiusXl)
)

/**
 * Extended colors for custom use cases
 */
data class ExtendedColors(
    val success: androidx.compose.ui.graphics.Color,
    val successContainer: androidx.compose.ui.graphics.Color,
    val warning: androidx.compose.ui.graphics.Color,
    val warningContainer: androidx.compose.ui.graphics.Color,
    val info: androidx.compose.ui.graphics.Color,
    val infoContainer: androidx.compose.ui.graphics.Color,
    val favorite: androidx.compose.ui.graphics.Color,
    val favoriteInactive: androidx.compose.ui.graphics.Color,
    val shimmerBase: androidx.compose.ui.graphics.Color,
    val shimmerHighlight: androidx.compose.ui.graphics.Color,
    val cardBackground: androidx.compose.ui.graphics.Color,
    val textPrimary: androidx.compose.ui.graphics.Color,
    val textSecondary: androidx.compose.ui.graphics.Color,
    val textTertiary: androidx.compose.ui.graphics.Color,
    val textDisabled: androidx.compose.ui.graphics.Color
)

private val LightExtendedColors = ExtendedColors(
    success = AliceColors.Success,
    successContainer = AliceColors.SuccessLight,
    warning = AliceColors.Warning,
    warningContainer = AliceColors.WarningLight,
    info = AliceColors.Info,
    infoContainer = AliceColors.InfoLight,
    favorite = AliceColors.Favorite,
    favoriteInactive = AliceColors.FavoriteInactive,
    shimmerBase = AliceColors.ShimmerBaseLight,
    shimmerHighlight = AliceColors.ShimmerHighlightLight,
    cardBackground = AliceColors.CardBackgroundLight,
    textPrimary = AliceColors.TextPrimaryLight,
    textSecondary = AliceColors.TextSecondaryLight,
    textTertiary = AliceColors.TextTertiaryLight,
    textDisabled = AliceColors.TextDisabledLight
)

private val DarkExtendedColors = ExtendedColors(
    success = AliceColors.SuccessLight,
    successContainer = AliceColors.Success,
    warning = AliceColors.WarningLight,
    warningContainer = AliceColors.Warning,
    info = AliceColors.InfoLight,
    infoContainer = AliceColors.Info,
    favorite = AliceColors.Favorite,
    favoriteInactive = AliceColors.FavoriteInactive,
    shimmerBase = AliceColors.ShimmerBaseDark,
    shimmerHighlight = AliceColors.ShimmerHighlightDark,
    cardBackground = AliceColors.CardBackgroundDark,
    textPrimary = AliceColors.TextPrimaryDark,
    textSecondary = AliceColors.TextSecondaryDark,
    textTertiary = AliceColors.TextTertiaryDark,
    textDisabled = AliceColors.TextDisabledDark
)

/**
 * CompositionLocal for extended colors
 */
val LocalExtendedColors = staticCompositionLocalOf { LightExtendedColors }

/**
 * Alice theme composable that wraps MaterialTheme with custom colors and typography
 */
@Composable
fun AliceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val extendedColors = if (darkTheme) DarkExtendedColors else LightExtendedColors
    
    CompositionLocalProvider(
        LocalExtendedColors provides extendedColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AliceMaterialTypography,
            shapes = AliceShapes,
            content = content
        )
    }
}

/**
 * Extension property to access extended colors from MaterialTheme
 */
object AliceTheme {
    val extendedColors: ExtendedColors
        @Composable
        get() = LocalExtendedColors.current
}
