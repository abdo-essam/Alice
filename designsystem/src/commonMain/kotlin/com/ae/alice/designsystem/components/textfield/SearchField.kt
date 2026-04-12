package com.ae.alice.designsystem.components.textfield

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import alice.designsystem.generated.resources.Res
import alice.designsystem.generated.resources.ic_delete_search
import alice.designsystem.generated.resources.ic_search
import com.ae.alice.designsystem.theme.Theme
import org.jetbrains.compose.resources.painterResource
import androidx.compose.ui.graphics.Color

@Composable
fun SearchField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    onClear: () -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    focusRequester: FocusRequester = FocusRequester(),
    onFocusChanged: (Boolean) -> Unit = {},
    containerColor: Color = Theme.colorScheme.background.surface,
) {
    BasicTextField(
        value = value,
        onValueChanged = onValueChange,
        hint = placeholder,
        leadingIcon = painterResource(Res.drawable.ic_search),
        leadingIconTint = Theme.colorScheme.shadeSecondary,
        trailingIcon = if (value.isNotEmpty()) painterResource(Res.drawable.ic_delete_search) else null,
        onTrailingIconClick = if (value.isNotEmpty()) onClear else null,
        showTrailingDivider = false,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        focusRequester = focusRequester,
        onFocusChanged = onFocusChanged,
        containerColor = containerColor,
        modifier = modifier,
    )
}
