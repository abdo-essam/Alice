package com.ae.alice.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AProgressBar(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    strokeCap: StrokeCap = StrokeCap.Round,
    gapSize: Dp = 4.dp,
) {
    LinearProgressIndicator(
        modifier = modifier,
        trackColor = trackColor,
        color = color,
        strokeCap = strokeCap,
        gapSize = gapSize,
    )
}

@Composable
fun AProgressBar(
    progress: () -> Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    strokeCap: StrokeCap = StrokeCap.Round,
    gapSize: Dp = 4.dp,
) {
    LinearProgressIndicator(
        modifier = modifier,
        trackColor = trackColor,
        color = color,
        strokeCap = strokeCap,
        gapSize = gapSize,
        progress = progress,
    )
}
