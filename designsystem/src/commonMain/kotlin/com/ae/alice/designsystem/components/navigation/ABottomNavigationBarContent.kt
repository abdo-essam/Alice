package com.ae.alice.designsystem.components.navigation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
internal fun ABottomNavigationBarContent(
    items: List<ABottomNavigationItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    indicatorColor: Color = MaterialTheme.colorScheme.primary,
) {
    val density = LocalDensity.current
    var totalWidth by remember { mutableStateOf(0) }

    val itemWidth = if (items.isNotEmpty() && totalWidth > 0) {
        with(density) { (totalWidth / items.size).toDp() }
    } else 0.dp

    val indicatorOffset by animateDpAsState(
        targetValue = itemWidth * selectedIndex + (itemWidth - 40.dp) / 2
    )

    Box(modifier = modifier.onSizeChanged { totalWidth = it.width }) {
        // Indicator bar at top
        Box(
            modifier = Modifier
                .offset(x = indicatorOffset)
                .width(40.dp)
                .height(3.dp)
                .background(
                    indicatorColor,
                    RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)
                )
        )

        // Navigation items
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items.forEachIndexed { index, item ->
                ABottomNavigationBarItem(
                    item = item,
                    isSelected = selectedIndex == index,
                    onClick = {
                        onItemSelected(index)
                        item.entry()
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
