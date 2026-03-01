package com.ae.alice.designsystem.util

import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.material3.ripple
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

/**
 * Convenience wrapper around Material 3 [ripple] for consistent usage.
 */
fun rippleIndication(
    bounded: Boolean = true,
    radius: Dp = Dp.Unspecified,
    color: Color = Color.Unspecified
): IndicationNodeFactory {
    return ripple(bounded, radius, color)
}
