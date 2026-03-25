package com.ae.alice.designsystem.components.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.SubcomposeAsyncImage
import alice.designsystem.generated.resources.Res
import alice.designsystem.generated.resources.ic_delete_search
import org.jetbrains.compose.resources.painterResource
import com.ae.alice.designsystem.components.icon.Icon
import com.ae.alice.designsystem.components.indicator.DotsProgressIndicator
import com.ae.alice.designsystem.theme.Theme
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.dp

@Composable
fun NetworkImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
) {
    if (url.isBlank()) {
        ErrorPlaceholder(modifier = modifier)
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
            ErrorPlaceholder(modifier = Modifier.matchParentSize())
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

@Composable
private fun ErrorPlaceholder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(Theme.colorScheme.background.surfaceHigh),
        contentAlignment = Alignment.Center,
    ) {
        // You can replace this with a better broken-image icon if available
        Icon(
            painter = painterResource(Res.drawable.ic_delete_search),
            contentDescription = "Error loading image",
            tint = Theme.colorScheme.shadeTertiary,
            modifier = Modifier.size(24.dp)
        )
    }
}