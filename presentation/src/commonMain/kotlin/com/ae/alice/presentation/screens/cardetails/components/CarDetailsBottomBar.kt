package com.ae.alice.presentation.screens.cardetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ae.alice.designsystem.components.button.PrimaryButton
import com.ae.alice.designsystem.theme.Theme
import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.car_details_get_car
import org.jetbrains.compose.resources.stringResource

/**
 * Bottom action bar for car details — single CTA button.
 * Sits inside the MENA Scaffold's bottomBar slot which already handles navigation bar padding.
 */
@Composable
fun CarDetailsBottomBar(
    onGetCarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Theme.colorScheme.background.surface)
            .padding(
                horizontal = Theme.spacing._16,
                vertical = Theme.spacing._12
            )
    ) {
        PrimaryButton(
            text = stringResource(Res.string.car_details_get_car),
            onClick = onGetCarClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
