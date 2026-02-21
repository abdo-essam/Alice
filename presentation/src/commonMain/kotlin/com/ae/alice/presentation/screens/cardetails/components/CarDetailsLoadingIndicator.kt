package com.ae.alice.presentation.screens.cardetails.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.theme.ATheme

@Composable
fun CarDetailsLoadingIndicator(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = ATheme.colors.Primary,
            modifier = Modifier.size(48.dp),
            strokeWidth = 4.dp
        )
    }
}
