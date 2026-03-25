package com.ae.alice.designsystem.components.snackbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.icon.Icon
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme
import kotlinx.coroutines.delay

/**
 * Themed snackbar with slide + fade animation.
 *
 * Auto-dismisses after [displayDurationMs]. Tapping the bar triggers
 * [onClick] then [onDismiss].
 */
@Composable
fun SnackBar(
    isVisible: Boolean,
    title: String,
    message: String,
    leadingIcon: Painter,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified,
    contentDescription: String? = null,
    displayDurationMs: Long = 3000L,
    hideAnimationDelayMs: Long = 200L,
    onDismiss: () -> Unit,
    onClick: () -> Unit = {},
) {
    LaunchedEffect(isVisible) {
        if (isVisible) {
            delay(displayDurationMs)
            delay(hideAnimationDelayMs)
            onDismiss()
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Theme.spacing._8),
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(Theme.radius.md))
                .background(Theme.colorScheme.background.surfaceLow)
                .clickable {
                    onClick()
                    onDismiss()
                }
                .padding(
                    top = Theme.spacing._8,
                    bottom = Theme.spacing._8,
                    start = Theme.spacing._12,
                    end = Theme.spacing._24
                )
        ) {
            Icon(
                painter = leadingIcon,
                contentDescription = contentDescription,
                modifier = Modifier.size(28.dp),
                tint = tint
            )

            Column {
                Text(
                    text = title,
                    style = Theme.typography.label.large,
                    color = Theme.colorScheme.shadePrimary,
                )

                Text(
                    text = message,
                    style = Theme.typography.body.small,
                    color = Theme.colorScheme.shadeSecondary
                )
            }
        }
    }
}
