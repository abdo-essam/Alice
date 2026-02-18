package com.ae.alice.designsystem.components.appbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.theme.ADimensions

@Composable
fun AAppBarOptionContainer(
    modifier: Modifier = Modifier,
    isBadgeVisible: Boolean = false,
    badgeColor: Color = MaterialTheme.colorScheme.primary,
    containerColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    shape: Shape = RoundedCornerShape(ADimensions.RadiusMd),
    badgeShape: Shape = RoundedCornerShape(50),
    iconContentPadding: PaddingValues = PaddingValues(10.dp),
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val clickableModifier = onClick?.let {
        Modifier.clickable(
            onClick = it,
            interactionSource = remember { MutableInteractionSource() },
            indication = ripple(),
        )
    } ?: Modifier

    Box(modifier = modifier.size(40.dp)) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(containerColor, shape)
                .clip(shape)
                .then(clickableModifier)
                .padding(iconContentPadding)
        ) {
            content()
        }
        AnimatedVisibility(
            visible = isBadgeVisible,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = (-1).dp)
        ) {
            Box(
                Modifier
                    .size(5.dp)
                    .background(badgeColor, badgeShape)
            )
        }
    }
}
