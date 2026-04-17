package com.ae.alice.presentation.screens.auth.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme

@Composable
fun PageDescription(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        style = Theme.typography.title.large,
        color = Theme.colorScheme.shadePrimary,
        modifier = modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = subtitle,
        style = Theme.typography.label.medium,
        color = Theme.colorScheme.shadeSecondary,
        modifier = modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(32.dp))
}
