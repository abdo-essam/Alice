package com.ae.alice.presentation.screens.auth.login

import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.auth_email_label
import alice.presentation.generated.resources.auth_forgot_password
import alice.presentation.generated.resources.auth_login_btn
import alice.presentation.generated.resources.auth_login_subtitle
import alice.presentation.generated.resources.auth_new_here
import alice.presentation.generated.resources.auth_password_label
import alice.presentation.generated.resources.auth_register_now
import alice.presentation.generated.resources.auth_welcome_back
import alice.presentation.generated.resources.ic_eye_hide
import alice.presentation.generated.resources.ic_eye_show
import alice.presentation.generated.resources.ic_lock
import alice.presentation.generated.resources.ic_mailbox
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
fun LoginScreen(
    onNavigateToMain: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToForgotPassword: () -> Unit = {},
    viewModel: LoginViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    var errorMsg by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                LoginEffect.NavigateToMain -> onNavigateToMain()
                LoginEffect.NavigateToRegister -> onNavigateToRegister()
                LoginEffect.NavigateToForgotPassword -> onNavigateToForgotPassword()
                is LoginEffect.ShowError -> {
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
                    onClick = { viewModel.processIntent(LoginIntent.SkipClicked) },
                    contentColor = Theme.colorScheme.shadeSecondary
                )
            }
            
            PageDescription(
                title = stringResource(Res.string.auth_welcome_back),
                subtitle = stringResource(Res.string.auth_login_subtitle)
            )

            Spacer(modifier = Modifier.height(32.dp))

            TextField(
                value = state.email,
                hint = stringResource(Res.string.auth_email_label),
                title = stringResource(Res.string.auth_email_label),
                leadingIcon = painterResource(Res.drawable.ic_mailbox),
                onValueChanged = { viewModel.processIntent(LoginIntent.EmailChanged(it)) },
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
                onValueChanged = { viewModel.processIntent(LoginIntent.PasswordChanged(it)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = if (state.isPasswordVisible) VisualTransformation.None
                                       else PasswordVisualTransformation(),
                trailingIcon = painterResource(
                    if (state.isPasswordVisible) Res.drawable.ic_eye_show
                    else Res.drawable.ic_eye_hide
                ),
                onTrailingIconClick = { viewModel.processIntent(LoginIntent.TogglePasswordVisibility) },
                modifier = Modifier.fillMaxWidth()
            )

            // Forgot password exactly like MENA App ForgetPasswordText
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Theme.spacing._4),
                contentAlignment = Alignment.BottomEnd
            ) {
                TextButton(
                    text = stringResource(Res.string.auth_forgot_password),
                    isEnabled = true,
                    onClick = { viewModel.processIntent(LoginIntent.ForgotPasswordClicked) },
                    contentColor = Theme.colorScheme.shadePrimary
                )
            }

            Spacer(modifier = Modifier.weight(1f, fill = false))
            Spacer(modifier = Modifier.height(24.dp))

            PrimaryButton(
                text = stringResource(Res.string.auth_login_btn),
                onClick = { viewModel.processIntent(LoginIntent.LoginClicked) },
                isEnabled = state.isLoginEnabled,
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
                message = stringResource(Res.string.auth_new_here),
                actionLabel = stringResource(Res.string.auth_register_now),
                onActionClick = { viewModel.processIntent(LoginIntent.RegisterClicked) }
            )
        }
    }
}
