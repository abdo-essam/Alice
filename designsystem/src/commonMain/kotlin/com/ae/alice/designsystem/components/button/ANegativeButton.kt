package com.ae.alice.designsystem.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.theme.ADimensions

@Composable
fun ANegativeButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null,
    trailingIcon: Painter? = null,
    contentDescription: String? = null,
    iconSize: Dp = 20.dp,
    iconStartPadding: Dp = 8.dp,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    containerColor: Color = MaterialTheme.colorScheme.error,
    contentColor: Color = MaterialTheme.colorScheme.onError,
    disabledContainerColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
    disabledContentColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
    contentPadding: PaddingValues = PaddingValues(
        horizontal = ADimensions.SpacingLg,
        vertical = 13.dp
    ),
    shape: Shape = RoundedCornerShape(ADimensions.RadiusMd),
) {
    AButton(
        isEnabled = isEnabled,
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContentColor = disabledContentColor,
        disabledContainerColor = disabledContainerColor,
        contentPadding = contentPadding,
        shape = shape,
        onClick = onClick,
        isLoading = isLoading,
        loadingColors = listOf(
            MaterialTheme.colorScheme.onError.copy(alpha = 0.5f),
            MaterialTheme.colorScheme.onError.copy(alpha = 0.7f),
            MaterialTheme.colorScheme.onError
        ),
        modifier = modifier,
    ) {
        ABaseButtonContent(
            text = text,
            trailingIcon = trailingIcon,
            contentDescription = contentDescription,
            iconSize = iconSize,
            iconStartPadding = iconStartPadding,
            contentColor = it,
        )
    }
}
