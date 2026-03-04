package com.ae.alice.designsystem.components.checkBox

import androidx.compose.ui.state.ToggleableState

/**
 * Cycles through the three [ToggleableState] values:
 * Off → On → Indeterminate → Off …
 */
fun ToggleableState.nextState() = when (this) {
    ToggleableState.Off -> ToggleableState.On
    ToggleableState.On -> ToggleableState.Indeterminate
    ToggleableState.Indeterminate -> ToggleableState.Off
}
