package com.ae.alice.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.ae.alice.core.designsystem.theme.AliceDimensions
import com.ae.alice.core.designsystem.theme.AliceTheme

/**
 * Standard card component following Alice design system
 */
@Composable
fun AliceCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(AliceDimensions.RadiusMd)
    
    if (onClick != null) {
        Card(
            onClick = onClick,
            modifier = modifier,
            shape = shape,
            colors = CardDefaults.cardColors(
                containerColor = AliceTheme.extendedColors.cardBackground
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = AliceDimensions.CardElevation,
                pressedElevation = AliceDimensions.CardElevationPressed
            )
        ) {
            content()
        }
    } else {
        Card(
            modifier = modifier,
            shape = shape,
            colors = CardDefaults.cardColors(
                containerColor = AliceTheme.extendedColors.cardBackground
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = AliceDimensions.CardElevation
            )
        ) {
            content()
        }
    }
}

/**
 * Elevated card with higher prominence
 */
@Composable
fun AliceElevatedCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(AliceDimensions.RadiusLg)
    
    if (onClick != null) {
        Card(
            onClick = onClick,
            modifier = modifier,
            shape = shape,
            colors = CardDefaults.cardColors(
                containerColor = AliceTheme.extendedColors.cardBackground
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = AliceDimensions.CardElevationPressed
            )
        ) {
            content()
        }
    } else {
        Card(
            modifier = modifier,
            shape = shape,
            colors = CardDefaults.cardColors(
                containerColor = AliceTheme.extendedColors.cardBackground
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = AliceDimensions.CardElevationPressed
            )
        ) {
            content()
        }
    }
}

/**
 * Surface container following design system
 */
@Composable
fun AliceSurface(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surface,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(AliceDimensions.RadiusMd))
            .background(color)
            .padding(AliceDimensions.CardPadding)
    ) {
        content()
    }
}
