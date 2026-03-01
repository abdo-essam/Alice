package com.ae.alice.designsystem.theme.radius

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Corner-radius tokens.
 * Provided via [LocalRadius] so they are overridable in tests and previews.
 */
@Immutable
data class Radius(
    val xxs: Dp = 2.dp,
    val xs: Dp = 4.dp,
    val sm: Dp = 8.dp,
    val md: Dp = 12.dp,
    val lg: Dp = 16.dp,
    val xl: Dp = 24.dp,
    val full: Dp = 1000.dp
)

internal val AliceRadius = Radius()
internal val LocalRadius = staticCompositionLocalOf { AliceRadius }
