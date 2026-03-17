package com.ae.alice.designsystem.components.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.ae.alice.designsystem.theme.Theme

/**
 * Network image component with placeholder and error states.
 * Uses Coil 3 AsyncImage for compatibility with IntrinsicSize layouts.
 */
@Composable
fun NetworkImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
) {
    if (url.isBlank()) {
        ImagePlaceholder(modifier = modifier)
        return
    }

    AsyncImage(
        model = url,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier,
        placeholder = ColorPainter(Theme.colorScheme.background.surfaceHigh),
        error = ColorPainter(Theme.colorScheme.background.surfaceHigh)
    )
}

@Composable
private fun ImagePlaceholder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(Theme.colorScheme.background.surfaceHigh),
        contentAlignment = Alignment.Center,
    ) {
        // Empty placeholder with surfaceHigh background
    }
}
