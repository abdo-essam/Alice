package com.ae.alice.designsystem.components.dropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.icon.Icon
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme

/**
 * MENA admin-panel-style dropdown selector.
 * Shows a trigger row that opens a scrollable dropdown menu with items.
 *
 * Usage:
 * ```
 * DropdownSelector(
 *     label = "Country",
 *     selectedValue = state.country,
 *     options = listOf("Egypt", "Saudi Arabia", "UAE"),
 *     onOptionSelected = { viewModel.selectCountry(it) }
 * )
 * ```
 */
@Composable
fun DropdownSelector(
    label: String,
    selectedValue: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        if (label.isNotEmpty()) {
            Text(
                text = label,
                style = Theme.typography.label.small,
                color = Theme.colorScheme.shadeSecondary,
                modifier = Modifier.padding(bottom = Theme.spacing._4)
            )
        }

        Box {
            // Trigger
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(Theme.radius.md))
                    .background(Theme.colorScheme.background.surfaceHigh)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { expanded = true }
                    )
                    .padding(
                        horizontal = Theme.spacing._12,
                        vertical = Theme.spacing._12
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedValue.ifEmpty { placeholder },
                    style = Theme.typography.body.small,
                    color = if (selectedValue.isEmpty())
                        Theme.colorScheme.shadeTertiary
                    else
                        Theme.colorScheme.shadePrimary,
                    modifier = Modifier.weight(1f)
                )
                // Chevron icon
                Text(
                    text = "▾",
                    style = Theme.typography.body.medium,
                    color = Theme.colorScheme.shadeSecondary,
                )
            }

            // Dropdown menu
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth(0.9f),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    if (label.isNotEmpty()) {
                        Text(
                            text = label,
                            style = Theme.typography.title.small,
                            color = Theme.colorScheme.shadePrimary,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(if (options.size > 6) 300.dp else (options.size * 48).dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                                .padding(horizontal = 12.dp),
                            verticalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            options.forEach { option ->
                                DropdownItem(
                                    text = option,
                                    isSelected = option == selectedValue,
                                    onClick = {
                                        onOptionSelected(option)
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DropdownItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Theme.radius.sm))
            .background(
                if (isSelected) Theme.colorScheme.brand.brand.copy(alpha = 0.1f)
                else Theme.colorScheme.background.surface
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(
                horizontal = Theme.spacing._12,
                vertical = Theme.spacing._12
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = Theme.typography.label.medium,
            color = if (isSelected) Theme.colorScheme.brand.brand
            else Theme.colorScheme.shadePrimary,
        )
    }
}
