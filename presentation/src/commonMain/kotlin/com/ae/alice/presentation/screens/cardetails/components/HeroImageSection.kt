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
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.ae.alice.designsystem.components.indicator.DotsProgressIndicator
import com.ae.alice.designsystem.theme.Theme

private const val HERO_ASPECT_RATIO = 1.4f

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
                        Theme.colorScheme.background.surfaceHigh,
                        Theme.colorScheme.background.surfaceLow
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
                .padding(Theme.spacing._24)
                .clip(RoundedCornerShape(Theme.radius.lg)),
            contentScale = ContentScale.Fit,
            loading = { DotsProgressIndicator() },
            error = {
                Icon(
                    imageVector = Icons.Outlined.BrokenImage,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = Theme.colorScheme.textDisabled
                )
            }
        )
    }
}
