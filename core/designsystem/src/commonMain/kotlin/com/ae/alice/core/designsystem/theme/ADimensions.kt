package com.ae.alice.core.designsystem.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Centralized dimensions and spacing - 4dp grid system.
 */
object ADimensions {
    
    // Base spacing unit
    private const val GRID = 4
    
    // Spacing
    val SpacingXxs: Dp = (GRID * 0.5).dp  // 2dp
    val SpacingXs: Dp = (GRID * 1).dp      // 4dp
    val SpacingSm: Dp = (GRID * 2).dp      // 8dp
    val SpacingMd: Dp = (GRID * 3).dp      // 12dp
    val SpacingLg: Dp = (GRID * 4).dp      // 16dp
    val SpacingXl: Dp = (GRID * 5).dp      // 20dp
    val SpacingXxl: Dp = (GRID * 6).dp     // 24dp
    val Spacing3xl: Dp = (GRID * 8).dp     // 32dp
    val Spacing4xl: Dp = (GRID * 10).dp    // 40dp
    
    // Screen padding
    val ScreenPaddingHorizontal: Dp = SpacingLg
    val ScreenPaddingVertical: Dp = SpacingLg
    
    // Card dimensions
    val CardPadding: Dp = SpacingLg
    val CardElevation: Dp = 2.dp
    
    // Corner radii
    val RadiusXs: Dp = 4.dp
    val RadiusSm: Dp = 8.dp
    val RadiusMd: Dp = 12.dp
    val RadiusLg: Dp = 16.dp
    val RadiusXl: Dp = 24.dp
    val RadiusFull: Dp = 1000.dp
    
    // Icon sizes
    val IconXs: Dp = 16.dp
    val IconSm: Dp = 20.dp
    val IconMd: Dp = 24.dp
    val IconLg: Dp = 32.dp
    val IconXl: Dp = 48.dp
    
    // Button dimensions
    val ButtonHeight: Dp = 48.dp
    val ButtonHeightSmall: Dp = 36.dp
    val ButtonMinWidth: Dp = 64.dp
    
    // Input field dimensions
    val InputHeight: Dp = 56.dp
    val InputHeightSmall: Dp = 44.dp
    
    // Brand card
    val BrandCardHeight: Dp = 120.dp
    val BrandLogoSize: Dp = 64.dp
    
    // Model card
    val ModelCardHeight: Dp = 200.dp
    val ModelImageHeight: Dp = 140.dp
    
    // Divider
    val DividerThickness: Dp = 1.dp
}
