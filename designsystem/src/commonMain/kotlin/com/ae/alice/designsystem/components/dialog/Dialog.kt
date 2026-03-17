package com.ae.alice.designsystem.components.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.scaffold.ScaffoldScope
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme

/**
 * Convenience dialog that displays a title, message, and action buttons.
 * Used inside a [ScaffoldScope] overlay.
 */
@Composable
fun ScaffoldScope.Dialog(
    title: String,
    message: String,
    isVisible: Boolean,
    onDismiss: () -> Unit,
    actionButtons: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    hasDismissButton: Boolean = true,
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true,
    onCancelClick: () -> Unit = {},
    contentColor: Color = Theme.colorScheme.background.surfaceLow,
    scrimColor: Color = Color.Black.copy(0.55f),
    dialogCornerShape: Shape = RoundedCornerShape(Theme.radius.xl),
    cancelBackgroundShape: Shape = RoundedCornerShape(Theme.radius.full),
    contentPadding: PaddingValues = PaddingValues(12.dp),
) {
    BasicDialog(
        onDismiss = onDismiss,
        isVisible = isVisible,
        hasDismissButton = hasDismissButton,
        dismissOnBackPress = dismissOnBackPress,
        dismissOnClickOutside = dismissOnClickOutside,
        contentColor = contentColor,
        scrimColor = scrimColor,
        dialogCornerShape = dialogCornerShape,
        cancelBackgroundShape = cancelBackgroundShape,
        contentPadding = contentPadding,
        onCancelClick = onCancelClick,
        actionButtons = actionButtons,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 12.dp)
        ) {
            Text(
                text = title,
                style = Theme.typography.title.medium,
                color = Theme.colorScheme.primary.primary,
            )
            Text(
                text = message,
                textAlign = TextAlign.Center,
                style = Theme.typography.body.small,
                color = Theme.colorScheme.shadeSecondary,
            )
        }
    }
}
