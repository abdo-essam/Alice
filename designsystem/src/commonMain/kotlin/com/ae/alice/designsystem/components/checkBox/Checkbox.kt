package com.ae.alice.designsystem.components.checkBox

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme

/**
 * Tri-state checkbox with animated colors and optional label.
 *
 * Supports [ToggleableState.On], [ToggleableState.Off], and
 * [ToggleableState.Indeterminate] (shown as a horizontal dash).
 */
@Composable
fun Checkbox(
    checkedState: ToggleableState,
    onCheckedChange: ((ToggleableState) -> Unit)?,
    modifier: Modifier = Modifier,
    label: String? = null,
    isEnabled: Boolean = true,
    shape: Shape = RoundedCornerShape(Theme.radius.xs),
    intermediateLineShape: Shape = RoundedCornerShape(Theme.radius.xxs),
    contentPadding: PaddingValues = PaddingValues(3.dp)
) {
    val containerColor by animateColorAsState(
        targetValue = if (isEnabled) Theme.colorScheme.brand.brand else Theme.colorScheme.disabled
    )
    val contentColor by animateColorAsState(
        targetValue = if (isEnabled) Theme.colorScheme.brand.onBrand else Theme.colorScheme.textDisabled
    )
    val uncheckedBorderColor by animateColorAsState(
        targetValue = if (checkedState == ToggleableState.Off)
            Theme.colorScheme.border.disabled else Color.Unspecified
    )
    val uncheckedContainerColor by animateColorAsState(
        targetValue = if (checkedState == ToggleableState.Off)
            Theme.colorScheme.background.surfaceLow else containerColor
    )
    val uncheckedBorderDp by animateDpAsState(
        targetValue = if (checkedState == ToggleableState.Off) 1.dp else 0.dp
    )
    val uncheckedLabelColor by animateColorAsState(
        targetValue = if (checkedState == ToggleableState.Off)
            Theme.colorScheme.shadeTertiary else Theme.colorScheme.shadePrimary
    )
    val labelColor by animateColorAsState(
        targetValue = if (isEnabled) uncheckedLabelColor else Theme.colorScheme.stroke
    )

    val clickableModifier = onCheckedChange?.let {
        Modifier.clickable(
            enabled = isEnabled,
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            role = Role.Checkbox
        ) { onCheckedChange(checkedState) }
    } ?: Modifier

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        Box(
            modifier = Modifier
                .size(18.dp)
                .background(color = uncheckedContainerColor, shape = shape)
                .border(uncheckedBorderDp, uncheckedBorderColor, shape)
                .clip(shape)
                .then(clickableModifier)
                .padding(contentPadding),
            contentAlignment = Alignment.Center
        ) {
            when (checkedState) {
                ToggleableState.On -> Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.size(12.dp)
                )

                ToggleableState.Indeterminate -> HorizontalDivider(
                    thickness = 2.dp,
                    color = contentColor,
                    modifier = Modifier
                        .size(10.dp, 2.dp)
                        .clip(intermediateLineShape)
                )

                ToggleableState.Off -> { /* empty */ }
            }
        }

        label?.let { text ->
            Text(
                text = text,
                color = labelColor,
                style = Theme.typography.label.medium
            )
        }
    }
}
