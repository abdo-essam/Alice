package com.ae.alice.designsystem.components.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import alice.designsystem.generated.resources.Res
import alice.designsystem.generated.resources.error_default_message
import alice.designsystem.generated.resources.error_default_title
import alice.designsystem.generated.resources.retry
import com.ae.alice.designsystem.components.button.PrimaryButton
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme
import org.jetbrains.compose.resources.stringResource

/**
 * Error state layout — error icon, title, message, and retry button.
 * Follows the MENA error state pattern used throughout presentation screens.
 */
@Composable
fun ErrorLayout(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(Res.string.error_default_title),
    message: String = stringResource(Res.string.error_default_message),
    retryText: String = stringResource(Res.string.retry),
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier.fillMaxSize().verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        androidx.compose.material3.Icon(
            imageVector = Icons.Filled.ErrorOutline,
            contentDescription = title,
            tint = Theme.colorScheme.error,
            modifier = Modifier
                .padding(bottom = Theme.spacing._24)
        )

        Text(
            text = title,
            style = Theme.typography.title.medium,
            color = Theme.colorScheme.shadePrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        )

        Text(
            text = message,
            modifier = Modifier.padding(
                top = Theme.spacing._8,
                bottom = Theme.spacing._24,
                start = 32.dp,
                end = 32.dp
            ),
            textAlign = TextAlign.Center,
            style = Theme.typography.body.small,
            color = Theme.colorScheme.shadeSecondary
        )

        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            text = retryText,
            onClick = onRetry,
        )
    }
}
