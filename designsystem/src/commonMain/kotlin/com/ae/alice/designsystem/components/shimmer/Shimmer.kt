package com.ae.alice.designsystem.components.shimmer

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.theme.Theme

/**
 * Creates a shimmer [Brush] for loading placeholders.
 */
@Composable
fun shimmerBrush(): Brush {
    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200),
            repeatMode = RepeatMode.Restart
        )
    )

    val shimmerColors = listOf(
        Theme.colorScheme.shimmerBase,
        Theme.colorScheme.shimmerHighlight,
        Theme.colorScheme.shimmerBase
    )

    return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnimation.value - 200f, translateAnimation.value - 200f),
        end = Offset(translateAnimation.value, translateAnimation.value)
    )
}

/**
 * Rectangular shimmer placeholder.
 */
@Composable
fun ShimmerBox(
    modifier: Modifier = Modifier,
    height: Dp = 20.dp,
    cornerRadius: Dp = Theme.radius.sm
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(cornerRadius))
            .background(shimmerBrush())
    )
}

/**
 * Circular shimmer placeholder for avatars / icons.
 */
@Composable
fun ShimmerCircle(
    size: Dp = 48.dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(shimmerBrush())
    )
}

/**
 * Pre-built card-shaped shimmer placeholder.
 */
@Composable
fun ShimmerCard(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Theme.radius.md))
            .background(Theme.colorScheme.background.surface)
            .padding(Theme.spacing._16)
    ) {
        ShimmerBox(height = 140.dp, cornerRadius = Theme.radius.md)
        Spacer(modifier = Modifier.height(Theme.spacing._12))
        ShimmerBox(height = 16.dp)
        Spacer(modifier = Modifier.height(Theme.spacing._8))
        ShimmerBox(height = 12.dp, modifier = Modifier.fillMaxWidth(0.6f))
    }
}

/**
 * Pre-built list-item shimmer placeholder with avatar.
 */
@Composable
fun ShimmerListItem(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(Theme.spacing._12)
    ) {
        ShimmerCircle(size = 48.dp)

        Spacer(modifier = Modifier.width(Theme.spacing._12))

        Column(modifier = Modifier.weight(1f)) {
            ShimmerBox(height = 14.dp)
            Spacer(modifier = Modifier.height(Theme.spacing._8))
            ShimmerBox(height = 10.dp, modifier = Modifier.fillMaxWidth(0.5f))
        }
    }
}
