package com.ae.alice.presentation.screens.auth.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme

@Composable
fun AuthPrompt(
    message: String,
    actionLabel: String,
    onActionClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            style = Theme.typography.label.medium,
            color = Theme.colorScheme.shadeTertiary
        )
        Text(
            text = actionLabel,
            style = Theme.typography.label.medium.copy(fontWeight = FontWeight.Bold),
            color = Theme.colorScheme.primary.primary,
            modifier = Modifier
                .padding(start = 4.dp)
                .clickable { onActionClick() }
        )
    }
}
