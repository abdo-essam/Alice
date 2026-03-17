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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme

/**
 * Pill-shaped selector with a label chip and a dropdown value.
 *
 * Design: `[Label | Selected Value ˅]`
 *
 * Enhanced with:
 * - Animated chevron rotation on expand
 * - Elevated dropdown with rounded corners
 * - Selected item highlight with brand accent
 * - Proper touch feedback
 */
@Composable
fun Selector(
    label: String,
    selectedValue: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    val chevronRotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f
    )

    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(Theme.radius.full),
                    ambientColor = Theme.colorScheme.shadeTertiary.copy(alpha = 0.08f),
                )
                .clip(RoundedCornerShape(Theme.radius.full))
                .background(Theme.colorScheme.background.surface)
                .clickable(
                    enabled = options.isNotEmpty(),
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { expanded = true }
                .padding(Theme.spacing._4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Label chip
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(Theme.radius.full))
                    .background(Theme.colorScheme.background.surfaceHigh)
                    .padding(horizontal = Theme.spacing._20, vertical = 10.dp)
            ) {
                Text(
                    text = label,
                    style = Theme.typography.label.large,
                    color = Theme.colorScheme.secondary.secondary
                )
            }

            // Selected value + chevron
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = Theme.spacing._12),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedValue.ifEmpty { "—" },
                    style = Theme.typography.body.medium,
                    color = if (selectedValue.isNotEmpty())
                        Theme.colorScheme.shadePrimary
                    else Theme.colorScheme.shadeTertiary,
                    modifier = Modifier.padding(end = Theme.spacing._4)
                )

                if (options.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        modifier = Modifier
                            .size(22.dp)
                            .rotate(chevronRotation),
                        tint = Theme.colorScheme.brand.brand
                    )
                }
            }
        }

        // Dropdown menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(x = 0.dp, y = Theme.spacing._4),
            modifier = Modifier
                .widthIn(min = 200.dp)
                .clip(RoundedCornerShape(Theme.radius.md))
                .background(Theme.colorScheme.background.surface)
        ) {
            options.forEach { option ->
                val isSelected = option == selectedValue
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            style = if (isSelected) Theme.typography.label.medium
                            else Theme.typography.body.medium,
                            color = if (isSelected) Theme.colorScheme.brand.brand
                            else Theme.colorScheme.shadePrimary,
                        )
                    },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(
                            if (isSelected) Modifier.background(
                                Theme.colorScheme.brand.brand.copy(alpha = 0.06f)
                            ) else Modifier
                        )
                )
            }
        }
    }
}
