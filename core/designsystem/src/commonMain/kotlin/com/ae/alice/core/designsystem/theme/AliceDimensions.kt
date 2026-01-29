package com.ae.alice.core.designsystem.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Alice app dimensions and spacing system.
 * All sizes are centralized here following the DRY principle.
 */
object AliceDimensions {
    
    // Spacing Scale (following 4dp grid system)
    val SpacingNone: Dp = 0.dp
    val SpacingXxs: Dp = 2.dp
    val SpacingXs: Dp = 4.dp
    val SpacingSm: Dp = 8.dp
    val SpacingMd: Dp = 12.dp
    val SpacingLg: Dp = 16.dp
    val SpacingXl: Dp = 24.dp
    val SpacingXxl: Dp = 32.dp
    val SpacingXxxl: Dp = 48.dp
    val SpacingHuge: Dp = 64.dp
    
    // Screen Padding
    val ScreenPaddingHorizontal: Dp = 16.dp
    val ScreenPaddingVertical: Dp = 16.dp
    
    // Card Dimensions
    val CardPadding: Dp = 16.dp
    val CardPaddingSmall: Dp = 12.dp
    val CardElevation: Dp = 2.dp
    val CardElevationPressed: Dp = 8.dp
    
    // Corner Radius
    val RadiusNone: Dp = 0.dp
    val RadiusXs: Dp = 4.dp
    val RadiusSm: Dp = 8.dp
    val RadiusMd: Dp = 12.dp
    val RadiusLg: Dp = 16.dp
    val RadiusXl: Dp = 24.dp
    val RadiusXxl: Dp = 32.dp
    val RadiusFull: Dp = 1000.dp // For pill shapes
    
    // Icon Sizes
    val IconXs: Dp = 16.dp
    val IconSm: Dp = 20.dp
    val IconMd: Dp = 24.dp
    val IconLg: Dp = 32.dp
    val IconXl: Dp = 40.dp
    val IconXxl: Dp = 48.dp
    val IconHuge: Dp = 64.dp
    
    // Button Dimensions
    val ButtonHeightSmall: Dp = 32.dp
    val ButtonHeightMedium: Dp = 40.dp
    val ButtonHeightLarge: Dp = 48.dp
    val ButtonPaddingHorizontal: Dp = 16.dp
    val ButtonPaddingVertical: Dp = 8.dp
    
    // Input Field Dimensions
    val InputHeight: Dp = 56.dp
    val InputHeightSmall: Dp = 48.dp
    
    // Avatar / Logo Sizes
    val AvatarXs: Dp = 24.dp
    val AvatarSm: Dp = 32.dp
    val AvatarMd: Dp = 40.dp
    val AvatarLg: Dp = 56.dp
    val AvatarXl: Dp = 80.dp
    val AvatarXxl: Dp = 120.dp
    
    // Brand Logo in Cards
    val BrandLogoSmall: Dp = 40.dp
    val BrandLogoMedium: Dp = 56.dp
    val BrandLogoLarge: Dp = 80.dp
    
    // Bottom Navigation
    val BottomNavHeight: Dp = 80.dp
    
    // Top App Bar
    val TopAppBarHeight: Dp = 64.dp
    
    // Divider
    val DividerThickness: Dp = 1.dp
    
    // Border Width
    val BorderThin: Dp = 1.dp
    val BorderMedium: Dp = 2.dp
    val BorderThick: Dp = 3.dp
    
    // Image Aspect Ratios (as height/width)
    const val AspectRatioCard: Float = 0.75f    // 3:4
    const val AspectRatioWide: Float = 0.5625f  // 9:16
    const val AspectRatioSquare: Float = 1f     // 1:1
    const val AspectRatioBanner: Float = 0.4f   // Banner style
    
    // Car Card Dimensions
    val CarCardHeight: Dp = 200.dp
    val CarCardImageHeight: Dp = 120.dp
    val CarListItemHeight: Dp = 80.dp
    
    // Grid
    val GridItemMinWidth: Dp = 160.dp
    val GridSpacing: Dp = 16.dp
    
    // Shimmer
    val ShimmerItemHeight: Dp = 80.dp
    val ShimmerCardHeight: Dp = 200.dp
}
