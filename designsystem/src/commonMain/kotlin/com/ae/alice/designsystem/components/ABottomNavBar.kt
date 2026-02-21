package com.ae.alice.designsystem.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import alice.designsystem.generated.resources.Res
import alice.designsystem.generated.resources.nav_home
import alice.designsystem.generated.resources.nav_search
import alice.designsystem.generated.resources.nav_favorites
import alice.designsystem.generated.resources.nav_account
import com.ae.alice.designsystem.theme.AColors
import org.jetbrains.compose.resources.stringResource

/**
 * Bottom navigation item data.
 */
data class ABottomNavItem(
    val icon: ImageVector,
    val selectedIcon: ImageVector,
    val label: String,
    val selected: Boolean = false
)

/**
 * Modern bottom navigation bar component with proper insets handling.
 */
@Composable
fun ABottomNavBar(
    items: List<ABottomNavItem>,
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit = {}
) {
    NavigationBar(
        modifier = modifier
            .fillMaxWidth(),
        containerColor = AColors.Light.Surface,
        tonalElevation = 0.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = item.selected,
                onClick = { onItemClick(index) },
                icon = {
                    Icon(
                        imageVector = if (item.selected) item.selectedIcon else item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 11.sp
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = AColors.Primary,
                    selectedTextColor = AColors.Primary,
                    unselectedIconColor = AColors.Light.TextSecondary,
                    unselectedTextColor = AColors.Light.TextSecondary,
                    indicatorColor = AColors.Primary.copy(alpha = 0.12f)
                )
            )
        }
    }
}

/**
 * Default navigation items matching app purpose:
 * Home (browse brands), Search, Favorites, Account.
 */
object ANavItems {
    @Composable
    fun homeItem() = ABottomNavItem(
        icon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        label = stringResource(Res.string.nav_home)
    )

    @Composable
    fun searchItem() = ABottomNavItem(
        icon = Icons.Outlined.Search,
        selectedIcon = Icons.Filled.Search,
        label = stringResource(Res.string.nav_search)
    )

    @Composable
    fun favoritesItem() = ABottomNavItem(
        icon = Icons.Outlined.FavoriteBorder,
        selectedIcon = Icons.Filled.FavoriteBorder,
        label = stringResource(Res.string.nav_favorites)
    )

    @Composable
    fun accountItem() = ABottomNavItem(
        icon = Icons.Outlined.AccountCircle,
        selectedIcon = Icons.Filled.AccountCircle,
        label = stringResource(Res.string.nav_account)
    )

    @Composable
    fun default(selectedIndex: Int = 0) = listOf(
        homeItem().copy(selected = selectedIndex == 0),
        searchItem().copy(selected = selectedIndex == 1),
        favoritesItem().copy(selected = selectedIndex == 2),
        accountItem().copy(selected = selectedIndex == 3)
    )
}

