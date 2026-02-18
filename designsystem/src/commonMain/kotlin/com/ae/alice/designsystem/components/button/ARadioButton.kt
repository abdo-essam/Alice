package com.ae.alice.designsystem.components.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.AText

@Composable
fun ARadioButton(
    isSelected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    label: String? = null,
    shape: Shape = RoundedCornerShape(50),
    isEnabled: Boolean = true,
) {
    val animatedBorderDp by animateDpAsState(
        targetValue = if (isSelected) 6.dp else 1.dp
    )

    val animatedSelectionBorderColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
    )

    val animatedDisabledBorderColor by animateColorAsState(
        targetValue = if (isSelected)
            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
    )

    val animatedBorderColor by animateColorAsState(
        targetValue = if (isEnabled) animatedSelectionBorderColor else animatedDisabledBorderColor
    )

    val animatedUnselectedContentColor by animateColorAsState(
        targetValue = if (isSelected || !isEnabled) Color.Unspecified
        else MaterialTheme.colorScheme.surfaceVariant
    )

    val animatedLabelColor by animateColorAsState(
        targetValue = when {
            !isEnabled -> MaterialTheme.colorScheme.outline
            isSelected -> MaterialTheme.colorScheme.onSurface
            else -> MaterialTheme.colorScheme.onSurfaceVariant
        }
    )

    val clickableModifier = onClick?.let {
        Modifier.clickable(
            enabled = isEnabled,
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            role = Role.RadioButton
        ) { onClick() }
    } ?: Modifier

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        Box(
            modifier = Modifier
                .size(18.dp)
                .background(animatedUnselectedContentColor, shape)
                .border(
                    width = animatedBorderDp,
                    color = animatedBorderColor,
                    shape = shape
                )
                .clip(shape)
                .then(clickableModifier),
        )

        label?.let { text ->
            AText(
                text = text,
                color = animatedLabelColor,
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}
