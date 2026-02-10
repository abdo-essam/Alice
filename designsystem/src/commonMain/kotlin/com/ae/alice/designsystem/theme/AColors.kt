package com.ae.alice.designsystem.theme

import androidx.compose.ui.graphics.Color

/**
 * Alice Design System Color Palette.
 * Colors extracted from Alice brand logo.
 */
object AColors {
    
    // Brand Primary - Copper/Bronze tones
    val Primary = Color(0xFFC4956A)
    val PrimaryDark = Color(0xFFB07A4F)
    val PrimaryLight = Color(0xFFD4A574)
    val OnPrimary = Color(0xFFFFFFFF)
    
    // Brand Secondary - Dark Brown/Charcoal
    val Secondary = Color(0xFF4A3C31)
    val SecondaryDark = Color(0xFF3A2C21)
    val SecondaryLight = Color(0xFF6A5C51)
    val OnSecondary = Color(0xFFFFFFFF)
    
    // Accent - Warm copper
    val Accent = Color(0xFFD4A574)
    val AccentLight = Color(0xFFE4C5A4)
    
    // Light Theme
    object Light {
        val Background = Color(0xFFFAFAFA)
        val Surface = Color(0xFFFFFFFF)
        val SurfaceVariant = Color(0xFFF5F0EB)
        val Card = Color(0xFFFFFFFF)
        val Divider = Color(0xFFE8E0D8)
        
        val TextPrimary = Color(0xFF4A3C31)
        val TextSecondary = Color(0xFF7A6C61)
        val TextDisabled = Color(0xFFB0A090)
        val TextHint = Color(0xFF9E9090)
    }
    
    // Dark Theme
    object Dark {
        val Background = Color(0xFF1A1512)
        val Surface = Color(0xFF2A2520)
        val SurfaceVariant = Color(0xFF3A3530)
        val Card = Color(0xFF302B26)
        val Divider = Color(0xFF4A4540)
        
        val TextPrimary = Color(0xFFFFFFFF)
        val TextSecondary = Color(0xFFD0C0B0)
        val TextDisabled = Color(0xFF7A7060)
        val TextHint = Color(0xFF908070)
    }
    
    // Semantic
    val Success = Color(0xFF4CAF50)
    val Warning = Color(0xFFFFC107)
    val Error = Color(0xFFF44336)
    val Info = Color(0xFF2196F3)
    
    // Borders
    val BorderLight = Color(0xFFE8E0D8)
    val BorderDark = Color(0xFF4A4540)
    
    // Shimmer
    val ShimmerBase = Color(0xFFE8E0D8)
    val ShimmerHighlight = Color(0xFFF5F0EB)
    val ShimmerBaseDark = Color(0xFF3A3530)
    val ShimmerHighlightDark = Color(0xFF4A4540)
}
