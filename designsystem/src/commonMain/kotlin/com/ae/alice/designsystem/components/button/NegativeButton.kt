package com.ae.alice.designsystem.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.button.content.BaseButtonContent
import com.ae.alice.designsystem.theme.Theme

@Composable
fun NegativeButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null,
    trailingIcon: Painter? = null,
    contentDescription: String? = null,
    iconSize: Dp = 20.dp,
    iconStartPadding: Dp = Theme.spacing._8,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    containerColor: Color = Theme.colorScheme.error,
    contentColor: Color = Theme.colorScheme.primary.onPrimary,
    disabledContainerColor: Color = Theme.colorScheme.disabled,
    disabledContentColor: Color = Theme.colorScheme.textDisabled,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = Theme.spacing._16,
        vertical = 13.dp
    ),
    shape: Shape = RoundedCornerShape(Theme.radius.md)
) {
    Button(
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
            Theme.colorScheme.primary.onPrimaryHint,
            Theme.colorScheme.primary.onPrimaryBody,
            Theme.colorScheme.primary.onPrimary
        ),
        modifier = modifier
    ) {
        BaseButtonContent(
            text = text,
            trailingIcon = trailingIcon,
            contentDescription = contentDescription,
            iconSize = iconSize,
            iconStartPadding = iconStartPadding,
            contentColor = it
        )
    }
}
