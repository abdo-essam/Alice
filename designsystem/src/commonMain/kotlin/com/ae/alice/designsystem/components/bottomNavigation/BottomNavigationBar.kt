package com.ae.alice.designsystem.components.bottomNavigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.ae.alice.designsystem.theme.Theme

/**
 * Bottom navigation bar using a DSL builder.
 *
 * Usage:
 * ```
 * BottomNavigationBar(selectedItemIndex = currentTab) {
 *     bottomNavigationItem(
 *         selectedIcon = painterResource(Res.drawable.ic_home_selected),
 *         notSelectedIcon = painterResource(Res.drawable.ic_home),
 *         title = "Home",
 *         entry = { navigateTo(Screen.Home) }
 *     )
 *     bottomNavigationItem(...)
 * }
 * ```
 */
@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    selectedItemIndex: Int = 0,
    content: @Composable BottomNavigationScope.() -> Unit = {},
) {
    val scope = remember { BottomNavigationScopeImpl() }.apply {
        items.clear()
        content()
    }

    BottomNavigationBarContent(
        items = scope.items,
        selectedItemIndex = selectedItemIndex,
        onItemClick = { item ->
            val index = scope.items.indexOf(item)
            scope.items[index].entry.invoke()
        },
        modifier = modifier.background(Theme.colorScheme.background.surfaceLow)
    )
}
