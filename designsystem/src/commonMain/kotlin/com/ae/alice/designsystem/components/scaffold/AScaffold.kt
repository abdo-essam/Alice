package com.ae.alice.designsystem.components.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.util.applyIf

@Composable
fun AScaffold(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackBar: @Composable () -> Unit = {},
    isFullScreen: Boolean = false,
    overlays: AScaffoldScope.() -> Unit = {},
    content: @Composable AScaffoldScope.() -> Unit,
) {
    val scope = remember { AScaffoldScopeImpl() }.apply {
        items.clear()
        overlays()
    }

    val hasVisibleOverlay = scope.items.any { it.isVisible }
    val systemBarsInsets = WindowInsets.systemBars

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .applyIf(!isFullScreen) {
                windowInsetsPadding(systemBarsInsets)
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .applyIf(hasVisibleOverlay) {
                    blur(10.dp)
                }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 0.dp)
            ) {
                topBar()

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    content(scope)
                }

                Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                    bottomBar()
                }
            }
        }

        scope.items.forEach { overlayItem ->
            overlayItem.content(scope, overlayItem.isVisible)
        }

        snackBar()
    }
}
