package com.ae.alice.designsystem.components.bottomsheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.theme.ADimensions

@Composable
fun ABottomSheet(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(topStart = ADimensions.RadiusXl, topEnd = ADimensions.RadiusXl),
    containerColor: Color = MaterialTheme.colorScheme.surface,
    scrimColor: Color = Color.Black.copy(alpha = 0.4f),
    showDragHandle: Boolean = true,
    stickyFooter: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    var dragAccumulator by remember { mutableStateOf(0f) }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(scrimColor)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onDismiss
                ),
            contentAlignment = Alignment.BottomCenter
        ) {
            AnimatedVisibility(
                visible = isVisible,
                enter = slideInVertically(
                    animationSpec = tween(300),
                    initialOffsetY = { it }
                ),
                exit = slideOutVertically(
                    animationSpec = tween(300),
                    targetOffsetY = { it }
                )
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .clip(shape)
                        .background(containerColor, shape)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {} // Prevent clicks from passing through
                        )
                        .pointerInput(Unit) {
                            detectVerticalDragGestures(
                                onDragEnd = {
                                    if (dragAccumulator > 100f) {
                                        onDismiss()
                                    }
                                    dragAccumulator = 0f
                                },
                                onVerticalDrag = { _, dragAmount ->
                                    dragAccumulator += dragAmount
                                }
                            )
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (showDragHandle) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(4.dp)
                                .clip(RoundedCornerShape(50))
                                .background(MaterialTheme.colorScheme.outline)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    content()

                    stickyFooter?.let { footer ->
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(containerColor)
                                .padding(ADimensions.SpacingMd)
                        ) {
                            footer()
                        }
                    }
                }
            }
        }
    }
}
