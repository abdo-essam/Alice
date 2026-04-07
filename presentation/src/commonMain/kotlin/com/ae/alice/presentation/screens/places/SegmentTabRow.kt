package com.ae.alice.presentation.screens.places

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme

/**
 * A lightweight segmented tab row without paging — suitable when tabs
 * filter shared content rather than providing separate pages.
 */
@Composable
fun SegmentTabRow(
    tabs: List<String>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(Theme.radius.md))
            .background(Theme.colorScheme.background.surfaceHigh)
    ) {
        tabs.forEachIndexed { index, title ->
            val isSelected = index == selectedIndex

            val backgroundColor by animateColorAsState(
                targetValue = if (isSelected) Theme.colorScheme.background.surface
                else Theme.colorScheme.background.surfaceHigh
            )
            val textColor by animateColorAsState(
                targetValue = if (isSelected) Theme.colorScheme.brand.brand
                else Theme.colorScheme.shadeSecondary
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(Theme.radius.md))
                    .background(backgroundColor)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onTabSelected(index) }
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    style = Theme.typography.label.large,
                    color = textColor,
                )
            }
        }
    }
}
