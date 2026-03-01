package com.ae.alice.designsystem.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.theme.Theme

/**
 * Custom styled tab row with animated color transitions — theme-aware.
 */
@Composable
fun ATabRow(
    tabs: List<String>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Theme.radius.full))
            .background(Theme.colorScheme.background.surfaceHigh)
            .padding(Theme.spacing._4),
        horizontalArrangement = Arrangement.spacedBy(Theme.spacing._4)
    ) {
        tabs.forEachIndexed { index, title ->
            val isSelected = index == selectedIndex
            val backgroundColor by animateColorAsState(
                targetValue = if (isSelected) {
                    Theme.colorScheme.brand.brand
                } else {
                    Theme.colorScheme.background.surfaceHigh
                },
                animationSpec = tween(durationMillis = 250),
                label = "tabBg"
            )
            val textColor by animateColorAsState(
                targetValue = if (isSelected) {
                    Theme.colorScheme.brand.onBrand
                } else {
                    Theme.colorScheme.shadeSecondary
                },
                animationSpec = tween(durationMillis = 250),
                label = "tabText"
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .clip(RoundedCornerShape(Theme.radius.full))
                    .background(backgroundColor)
                    .clickable { onTabSelected(index) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    style = Theme.typography.label.large,
                    color = textColor,
                    maxLines = 1
                )
            }
        }
    }
}
