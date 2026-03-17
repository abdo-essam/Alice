package com.ae.alice.designsystem.components.chip

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.icon.Icon
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme

/**
 * Selectable chip with animated selection state.
 *
 * When selected, the chip uses [Theme.colorScheme.brand.brand] as background
 * and [Theme.colorScheme.brand.onBrand] for text/icon.
 */
@Composable
fun Chip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    painter: Painter? = null,
    iconSize: Dp = 16.dp,
    isEnabled: Boolean = true,
    shape: Shape = RoundedCornerShape(Theme.radius.full)
) {
    val transition = updateTransition(isSelected)
    val containerColor by transition.animateColor(
        targetValueByState = { selected ->
            if (selected) Theme.colorScheme.brand.brand
            else Theme.colorScheme.background.surfaceLow
        }
    )
    val contentColor by transition.animateColor(
        targetValueByState = { selected ->
            if (selected) Theme.colorScheme.brand.onBrand
            else Theme.colorScheme.shadeSecondary
        }
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(shape)
            .then(
                if (isEnabled) Modifier.clickable(onClick = onClick) else Modifier
            )
            .background(if (isEnabled) containerColor else Theme.colorScheme.disabled)
            .padding(vertical = Theme.spacing._8, horizontal = Theme.spacing._12)
    ) {
        painter?.let { iconPainter ->
            Icon(
                painter = iconPainter,
                modifier = Modifier.size(iconSize),
                contentDescription = null,
                tint = if (isEnabled) contentColor else Theme.colorScheme.textDisabled
            )
        }

        Text(
            text = text,
            style = Theme.typography.label.small,
            color = if (isEnabled) contentColor else Theme.colorScheme.textDisabled,
        )
    }
}
