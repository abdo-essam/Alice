package com.ae.alice.designsystem.components.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.SubcomposeAsyncImage
import com.ae.alice.designsystem.components.indicator.DotsProgressIndicator
import com.ae.alice.designsystem.theme.Theme

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

    SubcomposeAsyncImage(
        model = url,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier,
        loading = {
            ImagePlaceholder(modifier = Modifier.matchParentSize())
        },
        error = {
            ImagePlaceholder(modifier = Modifier.matchParentSize())
        }
    )
}

@Composable
private fun ImagePlaceholder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(Theme.colorScheme.background.surfaceHigh),
        contentAlignment = Alignment.Center,
    ) {
        DotsProgressIndicator()
    }
}