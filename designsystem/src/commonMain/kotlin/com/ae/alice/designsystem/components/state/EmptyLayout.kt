package com.ae.alice.designsystem.components.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.button.PrimaryButton
import com.ae.alice.designsystem.components.icon.Icon
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme

/**
 * Full empty state layout — icon, title, message, and call-to-action button.
 * Follows the MENA LocationEmptySection pattern, but generalized.
 */
@Composable
fun EmptyLayout(
    icon: Painter,
    title: String,
    message: String,
    buttonText: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    iconContentDescription: String? = null,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier.fillMaxSize().verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = icon,
            modifier = Modifier
                .size(128.dp)
                .padding(bottom = Theme.spacing._24),
            contentDescription = iconContentDescription,
        )

        Text(
            text = title,
            style = Theme.typography.title.medium,
            color = Theme.colorScheme.shadePrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = message,
            modifier = Modifier.padding(top = Theme.spacing._8, bottom = Theme.spacing._24),
            textAlign = TextAlign.Center,
            style = Theme.typography.body.small,
            color = Theme.colorScheme.shadeSecondary
        )

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = buttonText,
            onClick = onButtonClick,
            isEnabled = !isLoading,
            isLoading = isLoading
        )
    }
}

/**
 * Simple empty state — just title and optional message, no icon or button.
 * Useful for empty lists, empty archive, etc.
 */
@Composable
fun EmptyLayout(
    title: String,
    modifier: Modifier = Modifier,
    message: String? = null,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(Theme.spacing._24),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = Theme.typography.title.medium,
            color = Theme.colorScheme.shadePrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        message?.let {
            Text(
                text = it,
                modifier = Modifier.padding(top = Theme.spacing._8),
                textAlign = TextAlign.Center,
                style = Theme.typography.body.small,
                color = Theme.colorScheme.shadeSecondary
            )
        }
    }
}
