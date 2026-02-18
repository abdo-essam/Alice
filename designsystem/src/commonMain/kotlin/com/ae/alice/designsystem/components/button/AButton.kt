package com.ae.alice.designsystem.components.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.ae.alice.designsystem.components.indicator.ADotsProgressIndicator
import com.ae.alice.designsystem.theme.ADimensions

@Composable
fun AButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    disabledContainerColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
    disabledContentColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
    borderStroke: BorderStroke? = null,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = ADimensions.SpacingLg,
        vertical = ADimensions.SpacingSm
    ),
    shape: Shape = RoundedCornerShape(ADimensions.RadiusMd),
    loadingColors: List<Color> = listOf(
        MaterialTheme.colorScheme.outline,
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
        MaterialTheme.colorScheme.primary
    ),
    content: @Composable RowScope.(Color) -> Unit,
) {
    val animatedContainerColor by animateColorAsState(
        targetValue = if (isEnabled) containerColor else disabledContainerColor
    )
    val animatedContentColor by animateColorAsState(
        targetValue = if (isEnabled) contentColor else disabledContentColor
    )

    androidx.compose.material3.Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnabled && !isLoading,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = animatedContainerColor,
            contentColor = animatedContentColor,
            disabledContainerColor = animatedContainerColor,
            disabledContentColor = animatedContentColor,
        ),
        border = borderStroke,
        contentPadding = contentPadding,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        if (isLoading) {
            ADotsProgressIndicator(colors = loadingColors)
        } else {
            content(animatedContentColor)
        }
    }
}
