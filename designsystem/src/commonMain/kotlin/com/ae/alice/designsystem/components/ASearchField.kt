package com.ae.alice.designsystem.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ae.alice.designsystem.theme.AColors

/**
 * Search field component with modern outlined style.
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
        modifier = modifier
            .fillMaxWidth(),
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
                contentDescription = null,
                tint = AColors.Primary
            )
        },
        trailingIcon = {
            if (value.isNotEmpty() && onClear != null) {
                IconButton(onClick = onClear) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = null,
                        tint = AColors.Light.TextSecondary
                    )
                }
            }
        },
        textStyle = TextStyle(
            fontSize = 14.sp,
            color = AColors.Light.TextPrimary
        ),
        singleLine = true,
        shape = RoundedCornerShape(1000.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = AColors.Light.Surface,
            unfocusedContainerColor = AColors.Light.Surface,
            focusedBorderColor = AColors.Primary,
            unfocusedBorderColor = AColors.BorderLight,
            cursorColor = AColors.Primary
        )
    )
}
