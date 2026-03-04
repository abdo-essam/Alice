package com.ae.alice.designsystem.components.segment

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme

/**
 * Individual tab button inside a [Segment].
 */
@Composable
internal fun RowScope.SegmentButton(
    option: String,
    isSelected: Boolean,
    onSelectChange: () -> Unit
) {
    val shape = RoundedCornerShape(Theme.radius.md)
    val animatedBgColor = animateColorAsState(
        targetValue = if (isSelected) Theme.colorScheme.background.surfaceLow
        else Theme.colorScheme.background.surfaceHigh,
    )

    Box(
        modifier = Modifier
            .height(40.dp)
            .weight(1f)
            .padding(4.dp)
            .shadow(
                elevation = if (isSelected) 20.dp else 0.dp,
                clip = false,
                shape = shape,
                spotColor = Color(0x00000003)
            )
            .then(
                if (isSelected) Modifier.border(
                    width = 0.5.dp,
                    shape = shape,
                    color = Theme.colorScheme.stroke
                ) else Modifier
            )
            .clip(shape)
            .background(animatedBgColor.value)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = Theme.colorScheme.brand.brand)
            ) { onSelectChange() },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = option,
            style = Theme.typography.label.medium,
            color = if (isSelected) Theme.colorScheme.shadePrimary
            else Theme.colorScheme.shadeSecondary,
        )
    }
}
