package com.ae.alice.presentation.screens.cardetails.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ae.alice.designsystem.components.APrimaryButton
import com.ae.alice.designsystem.theme.Theme
import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.car_details_get_car
import org.jetbrains.compose.resources.stringResource

@Composable
fun CarDetailsBottomBar(
    onGetCarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navigationBarPadding = WindowInsets.navigationBars.asPaddingValues()

    Surface(
        modifier = modifier.fillMaxWidth(),
        shadowElevation = 8.dp,
        color = Theme.colorScheme.background.surface
    ) {
        APrimaryButton(
            onClick = onGetCarClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = Theme.spacing._16,
                    end = Theme.spacing._16,
                    top = Theme.spacing._12,
                    bottom = Theme.spacing._12 + navigationBarPadding
                        .calculateBottomPadding()
                )
        ) {
            Icon(
                imageVector = Icons.Filled.DirectionsCar,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.size(Theme.spacing._8))
            Text(
                text = stringResource(Res.string.car_details_get_car),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }
    }
}
