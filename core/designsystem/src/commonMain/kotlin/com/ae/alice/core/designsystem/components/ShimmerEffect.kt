package com.ae.alice.core.designsystem.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import com.ae.alice.core.designsystem.theme.AliceDimensions
import com.ae.alice.core.designsystem.theme.AliceTheme

private const val SHIMMER_ANIMATION_DURATION = 1200

/**
 * Shimmer effect for loading states
 */
@Composable
fun shimmerBrush(): Brush {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(SHIMMER_ANIMATION_DURATION),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer_translate"
    )
    
    val shimmerColors = listOf(
        AliceTheme.extendedColors.shimmerBase,
        AliceTheme.extendedColors.shimmerHighlight,
        AliceTheme.extendedColors.shimmerBase
    )
    
    return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnimation - 200f, 0f),
        end = Offset(translateAnimation, 0f)
    )
}

/**
 * Shimmer placeholder box
 */
@Composable
fun ShimmerBox(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(AliceDimensions.RadiusSm))
            .background(shimmerBrush())
    )
}

/**
 * Shimmer card placeholder for list items
 */
@Composable
fun ShimmerListItem(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(AliceDimensions.ShimmerItemHeight)
            .clip(RoundedCornerShape(AliceDimensions.RadiusMd))
            .background(shimmerBrush())
    )
}

/**
 * Shimmer card placeholder for grid items
 */
@Composable
fun ShimmerCard(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(AliceDimensions.ShimmerCardHeight)
            .clip(RoundedCornerShape(AliceDimensions.RadiusMd))
            .background(shimmerBrush())
    )
}

/**
 * Shimmer text line placeholder
 */
@Composable
fun ShimmerText(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ShimmerBox(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(AliceDimensions.SpacingLg)
        )
        Spacer(modifier = Modifier.height(AliceDimensions.SpacingSm))
        ShimmerBox(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(AliceDimensions.SpacingMd)
        )
    }
}
