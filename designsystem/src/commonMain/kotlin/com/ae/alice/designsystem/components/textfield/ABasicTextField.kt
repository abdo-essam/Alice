package com.ae.alice.designsystem.components.textfield

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.AIcon
import com.ae.alice.designsystem.components.AText
import com.ae.alice.designsystem.theme.ADimensions
import androidx.compose.foundation.clickable

@Composable
fun ABasicTextField(
    value: String,
    hint: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: Painter? = null,
    trailingIcon: Painter? = null,
    leadingContent: (@Composable () -> Unit)? = null,
    title: String? = null,
    leadingIconTint: Color = MaterialTheme.colorScheme.onSurface,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    minLines: Int = 1,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    isError: Boolean = false,
    showTrailingDivider: Boolean = true,
    errorMessage: String? = null,
    shape: Shape = RoundedCornerShape(ADimensions.RadiusMd),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    focusRequester: FocusRequester = FocusRequester(),
    onFocusChanged: (Boolean) -> Unit = {},
    onTrailingIconClick: (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    maxCharacters: Int = Int.MAX_VALUE,
) {
    var isFocused by remember { mutableStateOf(false) }

    val borderColor by animateColorAsState(
        targetValue = when {
            isError -> MaterialTheme.colorScheme.error
            isFocused -> MaterialTheme.colorScheme.primary
            else -> Color.Transparent
        }
    )

    Column(modifier = modifier) {
        title?.let {
            AText(
                text = it,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape)
                .background(MaterialTheme.colorScheme.surfaceVariant, shape)
                .border(
                    width = if (isError || isFocused) 1.dp else 0.dp,
                    color = borderColor,
                    shape = shape
                )
        ) {
            leadingContent?.invoke()

            leadingIcon?.let { icon ->
                Spacer(modifier = Modifier.width(12.dp))
                AIcon(
                    painter = icon,
                    contentDescription = null,
                    tint = leadingIconTint,
                    modifier = Modifier.size(20.dp)
                )
            }

            if (leadingIcon != null && showTrailingDivider) {
                Spacer(modifier = Modifier.width(8.dp))
                HorizontalDivider(
                    modifier = Modifier
                        .height(20.dp)
                        .width(1.dp),
                    color = MaterialTheme.colorScheme.outline
                )
            }

            BasicTextField(
                value = value,
                onValueChange = { newValue ->
                    if (newValue.length <= maxCharacters) {
                        onValueChanged(newValue)
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        horizontal = 12.dp,
                        vertical = 14.dp
                    )
                    .focusRequester(focusRequester)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                        onFocusChanged(focusState.isFocused)
                    },
                enabled = enabled,
                readOnly = readOnly,
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface
                ),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = singleLine,
                minLines = minLines,
                maxLines = maxLines,
                visualTransformation = visualTransformation,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                interactionSource = remember { MutableInteractionSource() },
                decorationBox = { innerTextField ->
                    Box {
                        if (value.isEmpty()) {
                            AText(
                                text = hint,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                        innerTextField()
                    }
                }
            )

            trailingIcon?.let { icon ->
                val trailingModifier = if (onTrailingIconClick != null) {
                    Modifier.clickable(onClick = onTrailingIconClick)
                } else {
                    Modifier
                }
                AIcon(
                    painter = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = trailingModifier
                        .padding(end = 12.dp)
                        .size(20.dp)
                )
            }
        }

        if (isError && !errorMessage.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(4.dp))
            AText(
                text = errorMessage,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error,
            )
        }
    }
}
