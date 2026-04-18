package com.ae.alice.designsystem.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.button.content.BaseButtonContent
import com.ae.alice.designsystem.theme.Theme
import sv.lib.squircleshape.SquircleShape

/**
 * MENA-style PrimaryButton with SquircleShape and matching loading state colors.
 */
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    trailingIcon: Painter? = null,
    iconSize: Dp = 20.dp,
    iconStartPadding: Dp = Theme.spacing._8,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    containerColor: Color = Theme.colorScheme.primary.primary,
    disabledContainerColor: Color = Theme.colorScheme.primary.primary.copy(alpha = 0.5f),
    contentColor: Color = Theme.colorScheme.primary.onPrimary,
    disabledContentColor: Color = Theme.colorScheme.primary.onPrimary.copy(alpha = 0.8f),
    contentPadding: PaddingValues = PaddingValues(
        horizontal = Theme.spacing._16,
        vertical = 13.dp
    ),
    shape: Shape = SquircleShape(Theme.radius.md)
) {
    Button(
        isEnabled = isEnabled,
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContentColor = disabledContentColor,
        disabledContainerColor = disabledContainerColor,
        contentPadding = contentPadding,
        shape = shape,
        isLoading = isLoading,
        loadingColors = listOf(
            Theme.colorScheme.primary.onPrimaryHint,
            Theme.colorScheme.primary.onPrimaryBody,
            Theme.colorScheme.primary.onPrimary
        ),
        onClick = onClick,
        modifier = modifier
    ) {
        BaseButtonContent(
            text = text,
            contentColor = it,
            trailingIcon = trailingIcon,
            iconSize = iconSize,
            iconStartPadding = iconStartPadding
        )
    }
}
