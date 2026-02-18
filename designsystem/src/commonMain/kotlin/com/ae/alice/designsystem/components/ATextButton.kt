package com.ae.alice.designsystem.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.button.ABaseButtonContent
import com.ae.alice.designsystem.components.button.AButton
import com.ae.alice.designsystem.theme.ADimensions

/**
 * Text button with loading and icon support.
 */
@Composable
fun ATextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    trailingIcon: Painter? = null,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    disabledContentColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    iconSize: Dp = 16.dp,
    iconStartPadding: Dp = 4.dp,
    overflow: TextOverflow = TextOverflow.Ellipsis,
) {
    AButton(
        onClick = onClick,
        isEnabled = isEnabled,
        isLoading = isLoading,
        containerColor = Color.Transparent,
        contentColor = contentColor,
        disabledContainerColor = Color.Transparent,
        disabledContentColor = disabledContentColor,
        contentPadding = contentPadding,
        shape = RoundedCornerShape(ADimensions.RadiusSm),
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
            overflow = overflow,
            contentColor = it,
        )
    }
}
