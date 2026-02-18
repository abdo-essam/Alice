package com.ae.alice.designsystem.components.textfield

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.AIcon
import com.ae.alice.designsystem.components.AText
import com.ae.alice.designsystem.theme.ADimensions

@Composable
fun AMobileNumberTextField(
    value: String,
    hint: String,
    leadingIcon: Painter,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    leadingContent: (@Composable () -> Unit)? = null,
    maxCharacters: Int = 16,
    title: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    errorMessage: String? = null,
    leadingIconTint: Color = MaterialTheme.colorScheme.onSurface,
    shape: Shape = RoundedCornerShape(ADimensions.RadiusMd),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    focusRequester: FocusRequester = FocusRequester(),
    onFocusChanged: (Boolean) -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    ABasicTextField(
        value = value,
        onValueChanged = onValueChanged,
        hint = hint,
        leadingIcon = leadingIcon,
        trailingIcon = null,
        title = title,
        leadingContent = leadingContent,
        leadingIconTint = leadingIconTint,
        enabled = enabled,
        readOnly = readOnly,
        isError = isError,
        errorMessage = errorMessage,
        shape = shape,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        focusRequester = focusRequester,
        onFocusChanged = onFocusChanged,
        visualTransformation = visualTransformation,
        modifier = modifier,
        maxCharacters = maxCharacters,
    )
}

@Composable
fun AMobileNumberLeadingContent(
    countryCode: String,
    countryPainter: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    arrowDownPainter: Painter? = null,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(ADimensions.RadiusMd))
            .clickable(onClick = onClick)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(vertical = 13.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = countryPainter,
            contentDescription = "country image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .size(20.dp)
        )

        AText(
            text = countryCode,
            style = MaterialTheme.typography.labelMedium.copy(
                textDirection = TextDirection.Ltr
            ),
            modifier = Modifier.padding(start = 4.dp, end = 2.dp),
            color = MaterialTheme.colorScheme.onSurface,
        )

        arrowDownPainter?.let { arrow ->
            AIcon(
                painter = arrow,
                contentDescription = "arrow down",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
