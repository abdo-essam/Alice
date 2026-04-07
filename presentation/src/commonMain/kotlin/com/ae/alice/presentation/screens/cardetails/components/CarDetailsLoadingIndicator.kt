package com.ae.alice.presentation.screens.cardetails.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ae.alice.designsystem.components.state.LoadingLayout

/**
 * Uses the design system's [LoadingLayout] (MENA dots indicator).
 */
@Composable
fun CarDetailsLoadingIndicator(
    modifier: Modifier = Modifier
) {
    LoadingLayout(modifier = modifier)
}
