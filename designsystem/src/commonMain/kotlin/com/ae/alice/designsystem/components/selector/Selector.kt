package com.ae.alice.designsystem.components.selector

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme

/**
 * Pill-shaped selector — no label, just value + chevron.
 * Tapping opens a bottom sheet (handled by caller).
 *
 * ┌──────────────────────────────────┐
 * │  ˅              الشارقة          │
 * └──────────────────────────────────┘
 */
@Composable
fun Selector(
    selectedValue: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    isExpanded: Boolean = false,
) {
    val chevronRotation by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f
    )

    val displayText = selectedValue.ifEmpty { placeholder }
    val textColor = if (selectedValue.isNotEmpty())
        Theme.colorScheme.shadePrimary
    else Theme.colorScheme.shadeTertiary

    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(Theme.radius.full),
                ambientColor = Theme.colorScheme.shadeTertiary.copy(alpha = 0.06f),
            )
            .clip(RoundedCornerShape(Theme.radius.full))
            .background(Theme.colorScheme.background.surface)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() }
            .padding(
                horizontal = Theme.spacing._16,
                vertical = Theme.spacing._12,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Chevron (start side)
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = null,
            modifier = Modifier
                .size(22.dp)
                .rotate(chevronRotation),
            tint = Theme.colorScheme.primary.primary
        )

        // Value (centered, fills remaining space)
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = displayText,
                style = Theme.typography.body.medium,
                color = textColor,
            )
        }
    }
}
