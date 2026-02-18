package com.ae.alice.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import alice.designsystem.generated.resources.Res
import alice.designsystem.generated.resources.alice_logo
import com.ae.alice.designsystem.theme.AColors
import org.jetbrains.compose.resources.painterResource

import alice.designsystem.generated.resources.account_title
import alice.designsystem.generated.resources.favorites_title
import alice.designsystem.generated.resources.home_title
import alice.designsystem.generated.resources.search_title
import alice.designsystem.generated.resources.settings_title
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

/**
 * Data for a single drawer item.
 */
data class ADrawerItem(
    val icon: ImageVector,
    val label: StringResource,
    val selected: Boolean = false
)

/**
 * Default drawer items matching app navigation.
 */
object ADrawerItems {
    fun default(selectedIndex: Int = 0) = listOf(
        ADrawerItem(Icons.Filled.Home, Res.string.home_title, selectedIndex == 0),
        ADrawerItem(Icons.Filled.Search, Res.string.search_title, selectedIndex == 1),
        ADrawerItem(Icons.Filled.FavoriteBorder, Res.string.favorites_title, selectedIndex == 2),
        ADrawerItem(Icons.Filled.AccountCircle, Res.string.account_title, selectedIndex == 3),
        ADrawerItem(Icons.Filled.Settings, Res.string.settings_title, selectedIndex == 4)
    )
}

/**
 * Styled drawer content with Alice branding.
 */
@Composable
fun ADrawerContent(
    items: List<ADrawerItem> = ADrawerItems.default(),
    onItemClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(280.dp)
            .background(AColors.Light.Surface)
    ) {
        // Branded header
        DrawerHeader()

        HorizontalDivider(color = AColors.Light.Divider, thickness = 1.dp)

        Spacer(modifier = Modifier.height(8.dp))

        // Menu items
        items.forEachIndexed { index, item ->
            DrawerMenuItem(
                item = item,
                onClick = { onItemClick(index) }
            )
        }
    }
}

@Composable
private fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        AColors.Secondary,
                        AColors.SecondaryDark
                    )
                )
            )
            .padding(20.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(Res.drawable.alice_logo),
                contentDescription = "Alice Logo",
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column {
                Text(
                    text = "ALICE",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = AColors.OnSecondary,
                    letterSpacing = 2.sp
                )
                Text(
                    text = "Car Discovery",
                    fontSize = 13.sp,
                    color = AColors.OnSecondary.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
private fun DrawerMenuItem(
    item: ADrawerItem,
    onClick: () -> Unit
) {
    val bgColor = if (item.selected) AColors.Primary.copy(alpha = 0.10f) else AColors.Light.Surface
    val contentColor = if (item.selected) AColors.Primary else AColors.Light.TextPrimary

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 2.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = stringResource(item.label),
            tint = contentColor,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = stringResource(item.label),
            fontSize = 15.sp,
            fontWeight = if (item.selected) FontWeight.SemiBold else FontWeight.Normal,
            color = contentColor
        )
    }
}
