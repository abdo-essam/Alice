package com.ae.alice.designsystem.components

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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp

fun getNextCheckboxState(current: ToggleableState): ToggleableState {
    return when (current) {
        ToggleableState.On -> ToggleableState.Off
        ToggleableState.Off -> ToggleableState.On
        ToggleableState.Indeterminate -> ToggleableState.On
    }
}

@Composable
fun ACheckbox(
    state: ToggleableState,
    onStateChange: (ToggleableState) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    isEnabled: Boolean = true,
    shape: Shape = RoundedCornerShape(4.dp),
) {
    val animatedBorderColor by animateColorAsState(
        targetValue = when {
            !isEnabled -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
            state == ToggleableState.Off -> MaterialTheme.colorScheme.outline
            else -> MaterialTheme.colorScheme.primary
        }
    )

    val animatedBackgroundColor by animateColorAsState(
        targetValue = when {
            !isEnabled && state != ToggleableState.Off ->
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
            state == ToggleableState.Off -> Color.Transparent
            else -> MaterialTheme.colorScheme.primary
        }
    )

    val animatedCheckSize by animateDpAsState(
        targetValue = when (state) {
            ToggleableState.On -> 12.dp
            ToggleableState.Indeterminate -> 8.dp
            ToggleableState.Off -> 0.dp
        }
    )

    val animatedLabelColor by animateColorAsState(
        targetValue = if (isEnabled)
            MaterialTheme.colorScheme.onSurface
        else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
    )

    val checkColor = MaterialTheme.colorScheme.onPrimary

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(18.dp)
                .background(animatedBackgroundColor, shape)
                .border(
                    width = if (state == ToggleableState.Off) 1.5.dp else 0.dp,
                    color = animatedBorderColor,
                    shape = shape
                )
                .clip(shape)
                .clickable(
                    enabled = isEnabled,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    role = Role.Checkbox,
                    onClick = { onStateChange(getNextCheckboxState(state)) }
                )
                .drawBehind {
                    if (state == ToggleableState.On) {
                        val checkSizePx = animatedCheckSize.toPx()
                        val strokeWidth = 2.dp.toPx()
                        val startX = (size.width - checkSizePx) / 2f
                        val startY = size.height / 2f
                        drawLine(
                            color = checkColor,
                            start = Offset(startX, startY),
                            end = Offset(startX + checkSizePx * 0.35f, startY + checkSizePx * 0.3f),
                            strokeWidth = strokeWidth
                        )
                        drawLine(
                            color = checkColor,
                            start = Offset(startX + checkSizePx * 0.35f, startY + checkSizePx * 0.3f),
                            end = Offset(startX + checkSizePx, startY - checkSizePx * 0.2f),
                            strokeWidth = strokeWidth
                        )
                    } else if (state == ToggleableState.Indeterminate) {
                        val dashWidth = animatedCheckSize.toPx()
                        val strokeWidth = 2.dp.toPx()
                        drawLine(
                            color = checkColor,
                            start = Offset((size.width - dashWidth) / 2f, size.height / 2f),
                            end = Offset((size.width + dashWidth) / 2f, size.height / 2f),
                            strokeWidth = strokeWidth
                        )
                    }
                }
        ) {
            // Content is drawn via drawBehind modifier
        }

        label?.let { text ->
            AText(
                text = text,
                color = animatedLabelColor,
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}
