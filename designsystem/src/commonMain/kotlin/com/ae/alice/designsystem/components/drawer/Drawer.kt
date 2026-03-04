package com.ae.alice.designsystem.components.drawer

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
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import alice.designsystem.generated.resources.Res
import alice.designsystem.generated.resources.alice_logo
import alice.designsystem.generated.resources.drawer_account
import alice.designsystem.generated.resources.drawer_app_name
import alice.designsystem.generated.resources.drawer_favorites
import alice.designsystem.generated.resources.drawer_home
import alice.designsystem.generated.resources.drawer_logo_desc
import alice.designsystem.generated.resources.drawer_search
import alice.designsystem.generated.resources.drawer_subtitle
import com.ae.alice.designsystem.locale.LocalAppLocale
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

/**
 * Data for a single drawer item.
 */
data class DrawerItem(
    val icon: ImageVector,
    val label: String,
    val selected: Boolean = false
)

/**
 * Default drawer items.
 */
object DrawerItems {
    @Composable
    fun default(selectedIndex: Int = 0) = listOf(
        DrawerItem(Icons.Filled.Home, stringResource(Res.string.drawer_home), selectedIndex == 0),
        DrawerItem(Icons.Filled.Search, stringResource(Res.string.drawer_search), selectedIndex == 1),
        DrawerItem(Icons.Filled.FavoriteBorder, stringResource(Res.string.drawer_favorites), selectedIndex == 2),
        DrawerItem(Icons.Filled.AccountCircle, stringResource(Res.string.drawer_account), selectedIndex == 3)
    )
}

/**
 * Drawer content with Alice branding.
 */
@Composable
fun DrawerContent(
    items: List<DrawerItem> = DrawerItems.default(),
    onItemClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val localeState = LocalAppLocale.current

    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(280.dp)
            .background(Theme.colorScheme.background.surface)
    ) {
        DrawerHeader()

        HorizontalDivider(color = Theme.colorScheme.stroke, thickness = 1.dp)

        Spacer(modifier = Modifier.height(Theme.spacing._8))

        items.forEachIndexed { index, item ->
            DrawerMenuItem(item = item, onClick = { onItemClick(index) })
        }

        Spacer(modifier = Modifier.weight(1f))

        HorizontalDivider(color = Theme.colorScheme.stroke, thickness = 1.dp)

        DrawerMenuItem(
            item = DrawerItem(
                icon = Icons.Filled.Language,
                label = if (localeState.language.code == "ar") "English" else "العربية"
            ),
            onClick = { localeState.switchLanguage() }
        )

        Spacer(modifier = Modifier.height(Theme.spacing._16))
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
                        Theme.colorScheme.secondary.secondary,
                        Theme.colorScheme.secondary.secondary.copy(alpha = 0.85f)
                    )
                )
            )
            .padding(20.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(Res.drawable.alice_logo),
                contentDescription = stringResource(Res.string.drawer_logo_desc),
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column {
                Text(
                    text = stringResource(Res.string.drawer_app_name),
                    style = Theme.typography.title.large,
                    color = Theme.colorScheme.brand.onBrand
                )
                Text(
                    text = stringResource(Res.string.drawer_subtitle),
                    style = Theme.typography.body.small,
                    color = Theme.colorScheme.brand.onBrand.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
private fun DrawerMenuItem(
    item: DrawerItem,
    onClick: () -> Unit
) {
    val bgColor = if (item.selected) {
        Theme.colorScheme.brand.brand.copy(alpha = 0.10f)
    } else {
        Theme.colorScheme.background.surface
    }
    val contentColor = if (item.selected) {
        Theme.colorScheme.brand.brand
    } else {
        Theme.colorScheme.shadePrimary
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Theme.spacing._12, vertical = Theme.spacing._2)
            .clip(RoundedCornerShape(Theme.radius.md))
            .background(bgColor)
            .clickable(onClick = onClick)
            .padding(horizontal = Theme.spacing._16, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.label,
            tint = contentColor,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(Theme.spacing._16))

        Text(
            text = item.label,
            style = Theme.typography.body.medium,
            color = contentColor
        )
    }
}
