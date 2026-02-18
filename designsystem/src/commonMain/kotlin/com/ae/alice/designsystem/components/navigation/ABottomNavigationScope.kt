package com.ae.alice.designsystem.components.navigation

import androidx.compose.ui.graphics.painter.Painter

interface ABottomNavigationScope {
    fun bottomNavigationItem(
        notSelectedIcon: Painter,
        selectedIcon: Painter,
        title: String,
        entry: () -> Unit,
    ) {
        error("The method is not implemented")
    }
}

data class ABottomNavigationItem(
    val notSelectedIcon: Painter,
    val selectedIcon: Painter,
    val title: String,
    val entry: () -> Unit,
)

internal class ABottomNavigationScopeImpl : ABottomNavigationScope {
    val items = mutableListOf<ABottomNavigationItem>()

    override fun bottomNavigationItem(
        notSelectedIcon: Painter,
        selectedIcon: Painter,
        title: String,
        entry: () -> Unit,
    ) {
        items.add(ABottomNavigationItem(notSelectedIcon, selectedIcon, title, entry))
    }
}
