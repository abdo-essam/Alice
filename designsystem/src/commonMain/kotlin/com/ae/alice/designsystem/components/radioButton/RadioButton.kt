package com.ae.alice.designsystem.components.radioButton

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
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme

/**
 * Animated radio button with optional label.
 *
 * The border thickness animates between 1dp (unselected) and 6dp (selected).
 */
@Composable
fun RadioButton(
    isSelected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    label: String? = null,
    shape: Shape = RoundedCornerShape(Theme.radius.full),
    isEnabled: Boolean = true
) {
    val borderDp by animateDpAsState(
        targetValue = if (isSelected) 6.dp else 1.dp
    )
    val selectionBorderColor by animateColorAsState(
        targetValue = if (isSelected) Theme.colorScheme.brand.brand else Theme.colorScheme.stroke
    )
    val disabledBorderColor by animateColorAsState(
        targetValue = if (isSelected) Theme.colorScheme.disabled else Theme.colorScheme.border.disabled
    )
    val borderColor by animateColorAsState(
        targetValue = if (isEnabled) selectionBorderColor else disabledBorderColor
    )
    val unselectedContentColor by animateColorAsState(
        targetValue = if (isSelected || !isEnabled) Color.Unspecified
        else Theme.colorScheme.background.surfaceLow
    )
    val unselectedLabelColor by animateColorAsState(
        targetValue = if (isSelected) Theme.colorScheme.shadePrimary
        else Theme.colorScheme.shadeTertiary
    )
    val labelColor by animateColorAsState(
        targetValue = if (isEnabled) unselectedLabelColor else Theme.colorScheme.stroke
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
                .background(unselectedContentColor, shape)
                .border(width = borderDp, color = borderColor, shape = shape)
                .clip(shape)
                .then(clickableModifier),
        )

        label?.let { text ->
            Text(
                text = text,
                color = labelColor,
                style = Theme.typography.label.medium
            )
        }
    }
}
