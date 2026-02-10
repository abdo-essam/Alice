package com.ae.alice.designsystem.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ae.alice.designsystem.theme.AColors
import com.ae.alice.designsystem.theme.ADimensions

/**
 * Search field component with warm color palette styling.
 */
@Composable
fun ASearchField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search...",
    onClear: (() -> Unit)? = null
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(ADimensions.InputHeightSmall),
        placeholder = {
            Text(
                text = placeholder,
                color = AColors.Light.TextHint,
                fontSize = 14.sp
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search",
                tint = AColors.Light.TextSecondary
            )
        },
        trailingIcon = {
            if (value.isNotEmpty() && onClear != null) {
                IconButton(onClick = onClear) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "Clear",
                        tint = AColors.Light.TextSecondary
                    )
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(ADimensions.RadiusFull),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = AColors.Light.SurfaceVariant,
            unfocusedContainerColor = AColors.Light.SurfaceVariant,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = AColors.Primary,
            focusedTextColor = AColors.Light.TextPrimary,
            unfocusedTextColor = AColors.Light.TextPrimary
        )
    )
}
