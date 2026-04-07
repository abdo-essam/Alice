package com.ae.alice.designsystem.theme.spacing

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Spacing tokens based on a 4dp grid system.
 * Provided via [LocalSpacing] so they are overridable in tests and previews.
 */
@Immutable
data class Spacing(
    val _2: Dp = 2.dp,
    val _4: Dp = 4.dp,
    val _8: Dp = 8.dp,
    val _12: Dp = 12.dp,
    val _16: Dp = 16.dp,
    val _20: Dp = 20.dp,
    val _24: Dp = 24.dp,
    val _32: Dp = 32.dp,
    val _40: Dp = 40.dp
)

internal val AliceSpacing = Spacing()
internal val LocalSpacing = staticCompositionLocalOf { AliceSpacing }
