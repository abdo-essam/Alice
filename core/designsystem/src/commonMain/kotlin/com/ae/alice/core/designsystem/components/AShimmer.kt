package com.ae.alice.core.designsystem.components

import androidx.compose.animation.core.LinearEasing
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
import com.ae.alice.core.designsystem.theme.ADimensions
import com.ae.alice.core.designsystem.theme.ATheme

/**
 * Creates animated shimmer brush.
 */
@Composable
fun shimmerBrush(): Brush {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer_translate"
    )
    
    val colors = listOf(
        ATheme.extendedColors.shimmerBase,
        ATheme.extendedColors.shimmerHighlight,
        ATheme.extendedColors.shimmerBase
    )
    
    return Brush.linearGradient(
        colors = colors,
        start = Offset(translateAnimation.value - 500f, translateAnimation.value - 500f),
        end = Offset(translateAnimation.value, translateAnimation.value)
    )
}

/**
 * Shimmer box placeholder.
 */
@Composable
fun AShimmerBox(
    modifier: Modifier = Modifier,
    height: Dp = ADimensions.BrandCardHeight,
    cornerRadius: Dp = ADimensions.RadiusMd
) {
    Box(
        modifier = modifier
            .height(height)
            .clip(RoundedCornerShape(cornerRadius))
            .background(shimmerBrush())
    )
}

/**
 * Shimmer list item placeholder.
 */
@Composable
fun AShimmerListItem(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(ADimensions.SpacingMd)
    ) {
        // Avatar placeholder
        Box(
            modifier = Modifier
                .size(ADimensions.BrandLogoSize)
                .clip(CircleShape)
                .background(shimmerBrush())
        )
        
        Spacer(modifier = Modifier.width(ADimensions.SpacingMd))
        
        Column(modifier = Modifier.weight(1f)) {
            // Title placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(20.dp)
                    .clip(RoundedCornerShape(ADimensions.RadiusXs))
                    .background(shimmerBrush())
            )
            
            Spacer(modifier = Modifier.height(ADimensions.SpacingSm))
            
            // Subtitle placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(16.dp)
                    .clip(RoundedCornerShape(ADimensions.RadiusXs))
                    .background(shimmerBrush())
            )
        }
    }
}
