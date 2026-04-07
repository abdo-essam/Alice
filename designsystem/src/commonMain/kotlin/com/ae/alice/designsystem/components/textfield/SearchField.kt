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

/**
 * MENA-style search field — wraps [BasicTextField] with a search leading icon
 * and a conditional clear trailing icon. Matches MENA's SearchHeader TextField pattern.
 */
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
        modifier = modifier,
    )
}
