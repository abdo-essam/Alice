package com.ae.alice.core.designsystem.theme

import androidx.compose.ui.graphics.Color

/**
 * Centralized color palette for the Alice design system.
 */
object AColors {
    
    // Primary colors
    val Primary = Color(0xFF1E88E5)
    val PrimaryDark = Color(0xFF1565C0)
    val PrimaryLight = Color(0xFF64B5F6)
    val OnPrimary = Color(0xFFFFFFFF)
    
    // Secondary colors
    val Secondary = Color(0xFF424242)
    val SecondaryDark = Color(0xFF212121)
    val SecondaryLight = Color(0xFF757575)
    val OnSecondary = Color(0xFFFFFFFF)
    
    // Accent
    val Accent = Color(0xFFFF6F00)
    val AccentLight = Color(0xFFFFAB40)
    
    // Background colors - Light theme
    object Light {
        val Background = Color(0xFFF5F5F5)
        val Surface = Color(0xFFFFFFFF)
        val SurfaceVariant = Color(0xFFF0F0F0)
        val Card = Color(0xFFFFFFFF)
        val Divider = Color(0xFFE0E0E0)
        
        // Text colors
        val TextPrimary = Color(0xFF212121)
        val TextSecondary = Color(0xFF757575)
        val TextDisabled = Color(0xFFBDBDBD)
        val TextHint = Color(0xFF9E9E9E)
    }
    
    // Background colors - Dark theme
    object Dark {
        val Background = Color(0xFF121212)
        val Surface = Color(0xFF1E1E1E)
        val SurfaceVariant = Color(0xFF2D2D2D)
        val Card = Color(0xFF252525)
        val Divider = Color(0xFF424242)
        
        // Text colors
        val TextPrimary = Color(0xFFFFFFFF)
        val TextSecondary = Color(0xFFB0B0B0)
        val TextDisabled = Color(0xFF6E6E6E)
        val TextHint = Color(0xFF808080)
    }
    
    // Semantic colors
    val Success = Color(0xFF4CAF50)
    val Warning = Color(0xFFFFC107)
    val Error = Color(0xFFF44336)
    val Info = Color(0xFF2196F3)
    
    // Border colors
    val BorderLight = Color(0xFFE0E0E0)
    val BorderDark = Color(0xFF424242)
    
    // Shimmer colors
    val ShimmerBase = Color(0xFFE0E0E0)
    val ShimmerHighlight = Color(0xFFF5F5F5)
    val ShimmerBaseDark = Color(0xFF2D2D2D)
    val ShimmerHighlightDark = Color(0xFF3D3D3D)
}
