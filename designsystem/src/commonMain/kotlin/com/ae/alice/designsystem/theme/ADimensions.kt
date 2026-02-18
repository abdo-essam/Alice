package com.ae.alice.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Centralized dimensions and spacing — 4dp grid system.
 * Now provided via CompositionLocal for consistency with MENA pattern.
 */
@Immutable
data class AliceDimensions(
    // Spacing
    val spacingXxs: Dp = 2.dp,
    val spacingXs: Dp = 4.dp,
    val spacingSm: Dp = 8.dp,
    val spacingMd: Dp = 12.dp,
    val spacingLg: Dp = 16.dp,
    val spacingXl: Dp = 20.dp,
    val spacingXxl: Dp = 24.dp,
    val spacing3xl: Dp = 32.dp,
    val spacing4xl: Dp = 40.dp,

    // Screen padding
    val screenPaddingHorizontal: Dp = 16.dp,
    val screenPaddingVertical: Dp = 16.dp,

    // Card
    val cardPadding: Dp = 16.dp,
    val cardElevation: Dp = 2.dp,

    // Radius
    val radiusXs: Dp = 4.dp,
    val radiusSm: Dp = 8.dp,
    val radiusMd: Dp = 12.dp,
    val radiusLg: Dp = 16.dp,
    val radiusXl: Dp = 24.dp,
    val radiusFull: Dp = 1000.dp,

    // Icons
    val iconXs: Dp = 16.dp,
    val iconSm: Dp = 20.dp,
    val iconMd: Dp = 24.dp,
    val iconLg: Dp = 32.dp,
    val iconXl: Dp = 48.dp,

    // Buttons
    val buttonHeight: Dp = 48.dp,
    val buttonHeightSmall: Dp = 36.dp,
    val buttonMinWidth: Dp = 64.dp,

    // Input
    val inputHeight: Dp = 56.dp,
    val inputHeightSmall: Dp = 44.dp,

    // Brand/model card
    val brandCardHeight: Dp = 120.dp,
    val brandLogoSize: Dp = 64.dp,
    val modelCardHeight: Dp = 200.dp,
    val modelImageHeight: Dp = 140.dp,

    // Divider
    val dividerThickness: Dp = 1.dp
)

internal val AliceDimensionDefaults = AliceDimensions()
internal val LocalAliceDimensions = staticCompositionLocalOf { AliceDimensionDefaults }

/**
 * Backward-compatible static accessor.
 * Screens using [ADimensions.SpacingMd] will still compile.
 * New code should prefer [ATheme.dimens.spacingMd].
 */
object ADimensions {
    // Spacing — static backward compat
    val SpacingXxs: Dp = 2.dp
    val SpacingXs: Dp = 4.dp
    val SpacingSm: Dp = 8.dp
    val SpacingMd: Dp = 12.dp
    val SpacingLg: Dp = 16.dp
    val SpacingXl: Dp = 20.dp
    val SpacingXxl: Dp = 24.dp
    val Spacing3xl: Dp = 32.dp
    val Spacing4xl: Dp = 40.dp

    val ScreenPaddingHorizontal: Dp = 16.dp
    val ScreenPaddingVertical: Dp = 16.dp

    val CardPadding: Dp = 16.dp
    val CardElevation: Dp = 2.dp

    val RadiusXs: Dp = 4.dp
    val RadiusSm: Dp = 8.dp
    val RadiusMd: Dp = 12.dp
    val RadiusLg: Dp = 16.dp
    val RadiusXl: Dp = 24.dp
    val RadiusFull: Dp = 1000.dp

    val IconXs: Dp = 16.dp
    val IconSm: Dp = 20.dp
    val IconMd: Dp = 24.dp
    val IconLg: Dp = 32.dp
    val IconXl: Dp = 48.dp

    val ButtonHeight: Dp = 48.dp
    val ButtonHeightSmall: Dp = 36.dp
    val ButtonMinWidth: Dp = 64.dp

    val InputHeight: Dp = 56.dp
    val InputHeightSmall: Dp = 44.dp

    val BrandCardHeight: Dp = 120.dp
    val BrandLogoSize: Dp = 64.dp
    val ModelCardHeight: Dp = 200.dp
    val ModelImageHeight: Dp = 140.dp

    val DividerThickness: Dp = 1.dp
}
