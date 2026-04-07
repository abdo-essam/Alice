package com.ae.alice.designsystem.components.bottomNavigation

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

interface BottomNavigationScope {
    fun bottomNavigationItem(
        notSelectedIcon: Painter,
        selectedIcon: Painter,
        title: String,
        entry: () -> Unit,
    )

    fun bottomNavigationItem(
        notSelectedIcon: ImageVector,
        selectedIcon: ImageVector,
        title: String,
        entry: () -> Unit,
    )
}

data class BottomNavigationItem(
    val painterIcon: Painter? = null,
    val painterSelectedIcon: Painter? = null,
    val vectorIcon: ImageVector? = null,
    val vectorSelectedIcon: ImageVector? = null,
    val title: String,
    val entry: () -> Unit,
)

internal class BottomNavigationScopeImpl : BottomNavigationScope {
    val items = mutableListOf<BottomNavigationItem>()

    override fun bottomNavigationItem(
        notSelectedIcon: Painter,
        selectedIcon: Painter,
        title: String,
        entry: () -> Unit,
    ) {
        items.add(
            BottomNavigationItem(
                painterIcon = notSelectedIcon,
                painterSelectedIcon = selectedIcon,
                title = title,
                entry = entry
            )
        )
    }

    override fun bottomNavigationItem(
        notSelectedIcon: ImageVector,
        selectedIcon: ImageVector,
        title: String,
        entry: () -> Unit,
    ) {
        items.add(
            BottomNavigationItem(
                vectorIcon = notSelectedIcon,
                vectorSelectedIcon = selectedIcon,
                title = title,
                entry = entry
            )
        )
    }
}
