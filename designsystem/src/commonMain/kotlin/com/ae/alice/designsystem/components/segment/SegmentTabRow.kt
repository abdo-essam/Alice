package com.ae.alice.designsystem.components.segment

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme
import androidx.compose.ui.graphics.Color

/**
 * Segmented tab row for filtering content — no paging, just tab selection.
 *
 * Appearance:
 * ┌───────────────────────────────────┐
 * │  [ Tab A ]  ║  [ ▣ Tab B ]       │
 * └───────────────────────────────────┘
 *
 * The selected tab gets an elevated surface background with brand-colored text.
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
            .fillMaxWidth()
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(Theme.radius.lg),
                ambientColor = Theme.colorScheme.shadeTertiary.copy(alpha = 0.06f),
                spotColor = Theme.colorScheme.shadeTertiary.copy(alpha = 0.08f),
            )
            .clip(RoundedCornerShape(Theme.radius.lg))
            .background(Theme.colorScheme.background.surface)
            .padding(Theme.spacing._8),
    ) {
        tabs.forEachIndexed { index, title ->
            val isSelected = index == selectedIndex

            val backgroundColor by animateColorAsState(
                targetValue = if (isSelected) Theme.colorScheme.brand.brand
                else Color.Transparent,
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow)
            )
            val textColor by animateColorAsState(
                targetValue = if (isSelected) Theme.colorScheme.brand.onBrand
                else Theme.colorScheme.shadeSecondary,
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow)
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(Theme.radius.sm))
                    .background(backgroundColor)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onTabSelected(index) }
                    .padding(vertical = 12.dp, horizontal = Theme.spacing._8),
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
