package com.ae.alice.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Home
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
import com.ae.alice.designsystem.theme.AColors

/**
 * Bottom navigation item data.
 */
data class ABottomNavItem(
    val icon: ImageVector,
    val label: String,
    val selected: Boolean = false
)

/**
 * Bottom navigation bar component.
 */
@Composable
fun ABottomNavBar(
    items: List<ABottomNavItem>,
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit = {}
) {
    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
        containerColor = AColors.Light.Surface,
        tonalElevation = 8.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = item.selected,
                onClick = { onItemClick(index) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 10.sp
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = AColors.Primary,
                    selectedTextColor = AColors.Primary,
                    unselectedIconColor = AColors.Light.TextSecondary,
                    unselectedTextColor = AColors.Light.TextSecondary,
                    indicatorColor = AColors.Primary.copy(alpha = 0.1f)
                )
            )
        }
    }
}

/**
 * Default navigation items.
 */
object ANavItems {
    val Home = ABottomNavItem(Icons.Default.Home, "Home")
    val Messages = ABottomNavItem(Icons.Default.Email, "Messages")
    val Archive = ABottomNavItem(Icons.Default.Folder, "Archive")
    val Account = ABottomNavItem(Icons.Default.AccountCircle, "Account")
    
    fun default(selectedIndex: Int = 0) = listOf(
        Home.copy(selected = selectedIndex == 0),
        Messages.copy(selected = selectedIndex == 1),
        Archive.copy(selected = selectedIndex == 2),
        Account.copy(selected = selectedIndex == 3)
    )
}
