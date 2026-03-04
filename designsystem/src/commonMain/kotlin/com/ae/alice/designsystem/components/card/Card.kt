package com.ae.alice.designsystem.components.card

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.theme.Theme

/**
 * Base card — uses surface color from the color scheme so it adapts to dark mode.
 */
@Composable
fun Card(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.padding(Theme.spacing._8),
        shape = RoundedCornerShape(Theme.radius.md),
        colors = CardDefaults.cardColors(
            containerColor = Theme.colorScheme.background.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        content()
    }
}
