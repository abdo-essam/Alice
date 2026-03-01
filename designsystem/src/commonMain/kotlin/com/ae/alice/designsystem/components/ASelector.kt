package com.ae.alice.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.theme.Theme

/**
 * Pill-shaped selector with a label chip and a dropdown value.
 * Design: [Label | Selected Value ˅]
 */
@Composable
fun ASelector(
    label: String,
    selectedValue: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(Theme.radius.full))
                .background(Theme.colorScheme.background.surface)
                .clickable { expanded = true }
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

            // Selected value
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = Theme.spacing._12),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedValue,
                    style = Theme.typography.body.medium,
                    color = Theme.colorScheme.shadeSecondary,
                    modifier = Modifier.padding(end = Theme.spacing._4)
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Theme.colorScheme.brand.brand
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            style = Theme.typography.body.medium,
                            color = if (option == selectedValue) {
                                Theme.colorScheme.brand.brand
                            } else {
                                Theme.colorScheme.shadePrimary
                            }
                        )
                    },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
