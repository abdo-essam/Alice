package com.ae.alice.designsystem.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.theme.ADimensions
import kotlinx.coroutines.delay

@Composable
fun ASnackBar(
    isVisible: Boolean,
    title: String,
    modifier: Modifier = Modifier,
    message: String? = null,
    leadingIcon: Painter? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.inverseSurface,
    contentColor: Color = MaterialTheme.colorScheme.inverseOnSurface,
    autoDismissDuration: Long = 3000L,
    onDismiss: () -> Unit = {},
) {
    LaunchedEffect(isVisible) {
        if (isVisible) {
            delay(autoDismissDuration)
            onDismiss()
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            animationSpec = tween(300),
            initialOffsetY = { it }
        ),
        exit = slideOutVertically(
            animationSpec = tween(300),
            targetOffsetY = { it }
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = ADimensions.SpacingMd)
                .clip(RoundedCornerShape(ADimensions.RadiusMd))
                .background(backgroundColor)
                .padding(ADimensions.SpacingMd)
        ) {
            leadingIcon?.let { icon ->
                AIcon(
                    painter = icon,
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(ADimensions.SpacingSm))
            }

            Column(modifier = Modifier.weight(1f)) {
                AText(
                    text = title,
                    style = MaterialTheme.typography.labelLarge,
                    color = contentColor,
                )
                message?.let {
                    AText(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        color = contentColor.copy(alpha = 0.7f),
                    )
                }
            }
        }
    }
}
