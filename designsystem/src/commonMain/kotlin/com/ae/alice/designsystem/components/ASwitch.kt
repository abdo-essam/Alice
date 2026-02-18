package com.ae.alice.designsystem.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.theme.ADimensions

@Composable
fun ASwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    checkedTrackColor: Color = MaterialTheme.colorScheme.primary,
    uncheckedTrackColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    checkedThumbColor: Color = MaterialTheme.colorScheme.onPrimary,
    uncheckedThumbColor: Color = MaterialTheme.colorScheme.outline,
    disabledTrackColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
    disabledThumbColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
) {
    val trackColor by animateColorAsState(
        targetValue = when {
            !isEnabled -> disabledTrackColor
            checked -> checkedTrackColor
            else -> uncheckedTrackColor
        }
    )

    val thumbColor by animateColorAsState(
        targetValue = when {
            !isEnabled -> disabledThumbColor
            checked -> checkedThumbColor
            else -> uncheckedThumbColor
        }
    )

    val thumbOffset by animateDpAsState(
        targetValue = if (checked) 20.dp else 0.dp
    )

    Box(
        modifier = modifier
            .size(width = 44.dp, height = 24.dp)
            .clip(RoundedCornerShape(ADimensions.RadiusLg))
            .background(trackColor)
            .clickable(
                enabled = isEnabled,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onCheckedChange(!checked) },
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .offset(x = thumbOffset + 2.dp)
                .size(20.dp)
                .clip(RoundedCornerShape(50))
                .background(thumbColor)
        )
    }
}
