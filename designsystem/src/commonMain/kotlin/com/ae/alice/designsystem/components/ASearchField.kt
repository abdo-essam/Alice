package com.ae.alice.designsystem.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.ae.alice.designsystem.theme.Theme

/**
 * Search field component — pill-shaped, theme-aware colors.
 */
@Composable
fun ASearchField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search...",
    onClear: (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = placeholder,
                color = Theme.colorScheme.shadeTertiary,
                fontSize = 14.sp
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                tint = Theme.colorScheme.brand.brand
            )
        },
        trailingIcon = {
            if (value.isNotEmpty() && onClear != null) {
                IconButton(onClick = onClear) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = null,
                        tint = Theme.colorScheme.shadeSecondary
                    )
                }
            }
        },
        textStyle = Theme.typography.body.medium.copy(
            color = Theme.colorScheme.shadePrimary
        ),
        singleLine = true,
        shape = RoundedCornerShape(Theme.radius.full),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Theme.colorScheme.background.surface,
            unfocusedContainerColor = Theme.colorScheme.background.surface,
            focusedBorderColor = Theme.colorScheme.brand.brand,
            unfocusedBorderColor = Theme.colorScheme.stroke,
            cursorColor = Theme.colorScheme.brand.brand
        )
    )
}
