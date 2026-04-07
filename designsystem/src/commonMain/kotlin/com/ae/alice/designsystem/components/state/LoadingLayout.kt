package com.ae.alice.designsystem.components.state

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ae.alice.designsystem.components.indicator.DotsProgressIndicator
import com.ae.alice.designsystem.theme.Theme

/**
 * Full-screen loading state with animated dots (MENA pattern).
 */
@Composable
fun LoadingLayout(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Theme.colorScheme.background.surface
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        DotsProgressIndicator()
    }
}
