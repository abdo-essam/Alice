package com.ae.alice.presentation.screens.cardetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BrokenImage
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.ae.alice.designsystem.theme.ATheme

private const val HERO_ASPECT_RATIO = 1.4f
private val IMAGE_CORNER_RADIUS = 16.dp
private val LOADING_INDICATOR_SIZE = 32.dp
private val LOADING_STROKE_WIDTH = 3.dp
private val ERROR_ICON_SIZE = 64.dp

@Composable
fun HeroImageSection(
    imageUrl: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(HERO_ASPECT_RATIO)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        ATheme.colors.Light.SurfaceVariant,
                        ATheme.colors.Light.Background
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        SubcomposeAsyncImage(
            model = imageUrl.orEmpty(),
            contentDescription = contentDescription,
            modifier = Modifier
                .fillMaxWidth()
                .padding(ATheme.dimens.SpacingXxl)
                .clip(RoundedCornerShape(IMAGE_CORNER_RADIUS)),
            contentScale = ContentScale.Fit,
            loading = { ImageLoadingPlaceholder() },
            error = { ImageErrorPlaceholder() }
        )
    }
}

@Composable
private fun ImageLoadingPlaceholder() {
    CircularProgressIndicator(
        modifier = Modifier.size(LOADING_INDICATOR_SIZE),
        color = ATheme.colors.Primary,
        strokeWidth = LOADING_STROKE_WIDTH
    )
}

@Composable
private fun ImageErrorPlaceholder() {
    Icon(
        imageVector = Icons.Outlined.BrokenImage,
        contentDescription = null,
        modifier = Modifier.size(ERROR_ICON_SIZE),
        tint = ATheme.colors.Light.TextDisabled
    )
}
