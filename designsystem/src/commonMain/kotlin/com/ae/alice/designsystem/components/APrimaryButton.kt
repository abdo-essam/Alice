package com.ae.alice.designsystem.components

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
 * Primary filled button with loading and icon support.
 */
@Composable
fun APrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    trailingIcon: Painter? = null,
    iconSize: Dp = 20.dp,
    iconStartPadding: Dp = 8.dp,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
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
        containerColor = containerColor,
        contentColor = contentColor,
        contentPadding = contentPadding,
        shape = shape,
        modifier = modifier,
    ) {
        ABaseButtonContent(
            text = text,
            trailingIcon = trailingIcon,
            iconSize = iconSize,
            iconStartPadding = iconStartPadding,
            contentColor = it,
        )
    }
}

/**
 * Primary button with custom content.
 */
@Composable
fun APrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = ADimensions.SpacingLg,
        vertical = ADimensions.SpacingSm
    ),
    content: @Composable () -> Unit
) {
    AButton(
        onClick = onClick,
        modifier = modifier,
        isEnabled = enabled,
        contentPadding = contentPadding,
    ) {
        content()
    }
}
