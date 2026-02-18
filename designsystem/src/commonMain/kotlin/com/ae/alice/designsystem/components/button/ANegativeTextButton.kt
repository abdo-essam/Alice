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
fun ANegativeTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null,
    trailingIcon: Painter? = null,
    iconSize: Dp = 16.dp,
    contentDescription: String? = null,
    isEnabled: Boolean = true,
    contentColor: Color = MaterialTheme.colorScheme.error,
    disabledContentColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    iconStartPadding: Dp = 4.dp,
    shape: Shape = RoundedCornerShape(ADimensions.RadiusSm),
) {
    AButton(
        onClick = onClick,
        isEnabled = isEnabled,
        contentPadding = contentPadding,
        contentColor = contentColor,
        containerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        disabledContentColor = disabledContentColor,
        shape = shape,
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
