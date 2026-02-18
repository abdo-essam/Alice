package com.ae.alice.designsystem.components

import androidx.compose.foundation.BorderStroke
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
import com.ae.alice.designsystem.components.button.ABaseButtonContent
import com.ae.alice.designsystem.components.button.AButton
import com.ae.alice.designsystem.theme.ADimensions

/**
 * Outlined button with loading and icon support.
 */
@Composable
fun AOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    trailingIcon: Painter? = null,
    iconSize: Dp = 20.dp,
    contentDescription: String? = null,
    iconStartPadding: Dp = 8.dp,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    disabledContentColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
    contentPadding: PaddingValues = PaddingValues(
        horizontal = ADimensions.SpacingLg,
        vertical = 13.dp
    ),
    shape: Shape = RoundedCornerShape(ADimensions.RadiusMd),
) {
    AButton(
        onClick = onClick,
        isEnabled = isEnabled,
        isLoading = isLoading,
        containerColor = Color.Transparent,
        contentColor = contentColor,
        disabledContainerColor = Color.Transparent,
        disabledContentColor = disabledContentColor,
        borderStroke = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline),
        contentPadding = contentPadding,
        shape = shape,
        loadingColors = listOf(
            MaterialTheme.colorScheme.outline,
            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            MaterialTheme.colorScheme.primary
        ),
        modifier = modifier,
    ) {
        ABaseButtonContent(
            text = text,
            trailingIcon = trailingIcon,
            iconSize = iconSize,
            iconStartPadding = iconStartPadding,
            contentDescription = contentDescription,
            contentColor = it,
        )
    }
}
