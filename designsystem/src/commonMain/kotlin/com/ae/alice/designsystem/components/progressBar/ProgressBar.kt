package com.ae.alice.designsystem.components.progressBar

import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.theme.Theme

/**
 * Themed linear progress bar — indeterminate variant.
 */
@Composable
fun ProgressBar(
    modifier: Modifier = Modifier,
    color: Color = Theme.colorScheme.brand.brand,
    trackColor: Color = Theme.colorScheme.background.surfaceHigh,
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

/**
 * Themed linear progress bar — determinate variant.
 */
@Composable
fun ProgressBar(
    progress: () -> Float,
    modifier: Modifier = Modifier,
    color: Color = Theme.colorScheme.brand.brand,
    trackColor: Color = Theme.colorScheme.background.surfaceHigh,
    strokeCap: StrokeCap = StrokeCap.Round,
    gapSize: Dp = 4.dp,
) {
    LinearProgressIndicator(
        modifier = modifier,
        trackColor = trackColor,
        color = color,
        strokeCap = strokeCap,
        gapSize = gapSize,
        progress = progress
    )
}
