package com.ae.alice.presentation.screens.auth.register

import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.auth_already_have_account
import alice.presentation.generated.resources.auth_confirm_password_label
import alice.presentation.generated.resources.auth_create_account_subtitle
import alice.presentation.generated.resources.auth_display_name_label
import alice.presentation.generated.resources.auth_email_label
import alice.presentation.generated.resources.auth_login_now
import alice.presentation.generated.resources.auth_password_label
import alice.presentation.generated.resources.auth_register_btn
import alice.presentation.generated.resources.auth_register_title
import alice.presentation.generated.resources.ic_eye_hide
import alice.presentation.generated.resources.ic_eye_show
import alice.presentation.generated.resources.ic_lock
import alice.presentation.generated.resources.ic_mailbox
import alice.presentation.generated.resources.ic_profile
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.button.PrimaryButton
import com.ae.alice.designsystem.components.button.TextButton
import com.ae.alice.designsystem.components.scaffold.Scaffold
import com.ae.alice.designsystem.components.textfield.TextField
import com.ae.alice.designsystem.theme.Theme
import com.ae.alice.presentation.screens.auth.components.AuthPrompt
import com.ae.alice.presentation.screens.auth.components.GoogleSignInButton
import com.ae.alice.presentation.screens.auth.components.OrDivider
import com.ae.alice.presentation.screens.auth.components.PageDescription
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.setValue

@Composable
fun RegisterScreen(
    onNavigateToMain: () -> Unit,
    onNavigateToLogin: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    var errorMsg by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                RegisterEffect.NavigateToMain -> onNavigateToMain()
                RegisterEffect.NavigateToLogin -> onNavigateToLogin()
                is RegisterEffect.ShowError -> {
                    errorMsg = effect.message
                }
            }
        }
    }

    Scaffold(
        backgroundColor = Theme.colorScheme.background.surfaceLow,
        snackBar = {
            com.ae.alice.designsystem.components.snackbar.SnackBar(
                isVisible = errorMsg != null,
                title = "خطأ", // "Error" in Arabic
                message = errorMsg ?: "",
                leadingIcon = androidx.compose.ui.graphics.vector.rememberVectorPainter(Icons.Default.Warning),
                tint = Theme.colorScheme.error,
                onDismiss = { errorMsg = null }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                TextButton(
                    text = "تخطي", // TODO: add to string resources for skip
                    onClick = { viewModel.processIntent(RegisterIntent.SkipClicked) },
                    contentColor = Theme.colorScheme.shadeSecondary
                )
            }

            PageDescription(
                title = stringResource(Res.string.auth_register_title),
                subtitle = stringResource(Res.string.auth_create_account_subtitle)
            )

            Spacer(modifier = Modifier.height(32.dp))

            TextField(
                value = state.displayName,
                hint = stringResource(Res.string.auth_display_name_label),
                title = stringResource(Res.string.auth_display_name_label),
                leadingIcon = painterResource(Res.drawable.ic_profile),
                onValueChanged = { viewModel.processIntent(RegisterIntent.DisplayNameChanged(it)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = state.email,
                hint = stringResource(Res.string.auth_email_label),
                title = stringResource(Res.string.auth_email_label),
                leadingIcon = painterResource(Res.drawable.ic_mailbox),
                onValueChanged = { viewModel.processIntent(RegisterIntent.EmailChanged(it)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = state.password,
                hint = stringResource(Res.string.auth_password_label),
                title = stringResource(Res.string.auth_password_label),
                leadingIcon = painterResource(Res.drawable.ic_lock),
                onValueChanged = { viewModel.processIntent(RegisterIntent.PasswordChanged(it)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                visualTransformation = if (state.isPasswordVisible) VisualTransformation.None
                                       else PasswordVisualTransformation(),
                trailingIcon = painterResource(
                    if (state.isPasswordVisible) Res.drawable.ic_eye_show
                    else Res.drawable.ic_eye_hide
                ),
                onTrailingIconClick = { viewModel.processIntent(RegisterIntent.TogglePasswordVisibility) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = state.confirmPassword,
                hint = stringResource(Res.string.auth_confirm_password_label),
                title = stringResource(Res.string.auth_confirm_password_label),
                leadingIcon = painterResource(Res.drawable.ic_lock),
                onValueChanged = { viewModel.processIntent(RegisterIntent.ConfirmPasswordChanged(it)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = if (state.isConfirmPasswordVisible) VisualTransformation.None
                                       else PasswordVisualTransformation(),
                trailingIcon = painterResource(
                    if (state.isConfirmPasswordVisible) Res.drawable.ic_eye_show
                    else Res.drawable.ic_eye_hide
                ),
                onTrailingIconClick = { viewModel.processIntent(RegisterIntent.ToggleConfirmPasswordVisibility) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            PrimaryButton(
                text = stringResource(Res.string.auth_register_btn),
                onClick = { viewModel.processIntent(RegisterIntent.RegisterClicked) },
                isEnabled = state.isRegisterEnabled,
                isLoading = state.isLoading,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OrDivider()

            Spacer(modifier = Modifier.height(16.dp))

            com.mmk.kmpauth.google.GoogleButtonUiContainer(
                onGoogleSignInResult = { googleUser: com.mmk.kmpauth.google.GoogleUser? ->
                    if (googleUser != null) {
                        viewModel.loginWithGoogle(googleUser.idToken ?: "", null)
                    }
                }
            ) {
                GoogleSignInButton(
                    onClick = { this.onClick() },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            AuthPrompt(
                message = stringResource(Res.string.auth_already_have_account),
                actionLabel = stringResource(Res.string.auth_login_now),
                onActionClick = { viewModel.processIntent(RegisterIntent.LoginClicked) }
            )
        }
    }
}
