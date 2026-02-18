package com.ae.alice.designsystem.components.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.APrimaryButton
import com.ae.alice.designsystem.components.AText
import com.ae.alice.designsystem.components.ATextButton

@Composable
fun ADialog(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    message: String? = null,
    positiveButtonText: String? = null,
    negativeButtonText: String? = null,
    onPositiveClick: () -> Unit = {},
    onNegativeClick: () -> Unit = onDismiss,
    titleColor: Color = MaterialTheme.colorScheme.onSurface,
    messageColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    showDismissButton: Boolean = false,
) {
    ABasicDialog(
        isVisible = isVisible,
        onDismiss = onDismiss,
        showDismissButton = showDismissButton,
        modifier = modifier,
    ) {
        Column {
            title?.let {
                AText(
                    text = it,
                    style = MaterialTheme.typography.titleMedium,
                    color = titleColor,
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            message?.let {
                AText(
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = messageColor,
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            positiveButtonText?.let {
                APrimaryButton(
                    text = it,
                    onClick = onPositiveClick,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            negativeButtonText?.let {
                Spacer(modifier = Modifier.height(8.dp))
                ATextButton(
                    text = it,
                    onClick = onNegativeClick,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
