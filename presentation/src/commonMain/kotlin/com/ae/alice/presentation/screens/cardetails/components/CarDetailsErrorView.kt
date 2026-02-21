package com.ae.alice.presentation.screens.cardetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.theme.ATheme
import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.car_details_error_default
import alice.presentation.generated.resources.car_details_retry
import org.jetbrains.compose.resources.stringResource

@Composable
fun CarDetailsErrorView(
    errorMessage: String?,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = ATheme.dimens.ScreenPaddingHorizontal)
        ) {
            Icon(
                imageVector = Icons.Outlined.ErrorOutline,
                contentDescription = null,
                modifier = Modifier.size(56.dp),
                tint = ATheme.colors.Error.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(ATheme.dimens.SpacingMd))

            Text(
                text = errorMessage
                    ?: stringResource(Res.string.car_details_error_default),
                style = ATheme.typography.BodyLarge,
                color = ATheme.colors.Light.TextSecondary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(ATheme.dimens.SpacingXl))

            TextButton(onClick = onRetryClick) {
                Text(
                    text = stringResource(Res.string.car_details_retry),
                    style = ATheme.typography.BodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = ATheme.colors.Primary
                )
            }
        }
    }
}
