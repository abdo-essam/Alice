package com.ae.alice.presentation.screens.cardetails.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ae.alice.designsystem.components.state.ErrorLayout
import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.car_details_error_default
import org.jetbrains.compose.resources.stringResource

/**
 * Uses the design system's [ErrorLayout] with retry.
 */
@Composable
fun CarDetailsErrorView(
    errorMessage: String?,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ErrorLayout(
        title = errorMessage ?: stringResource(Res.string.car_details_error_default),
        onRetry = onRetryClick,
        modifier = modifier
    )
}
