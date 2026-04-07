package com.ae.alice.designsystem.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Conditionally applies [newModifiers] only when [condition] is true.
 */
@Composable
fun Modifier.applyIf(
    condition: Boolean,
    newModifiers: @Composable Modifier.() -> Modifier
): Modifier = if (condition) this.newModifiers() else this
