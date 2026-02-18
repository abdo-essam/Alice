package com.ae.alice.designsystem.util

import androidx.compose.ui.Modifier

fun Modifier.applyIf(condition: Boolean, modifier: Modifier.() -> Modifier): Modifier {
    return if (condition) this.modifier() else this
}
