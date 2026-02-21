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
import com.ae.alice.designsystem.theme.ATheme
import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.car_details_get_car
import org.jetbrains.compose.resources.stringResource

private val ICON_SIZE = 20.dp
private val BUTTON_FONT_SIZE = 16.sp

@Composable
fun CarDetailsBottomBar(
    onGetCarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navigationBarPadding = WindowInsets.navigationBars.asPaddingValues()

    Surface(
        modifier = modifier.fillMaxWidth(),
        shadowElevation = 8.dp,
        color = ATheme.colors.Light.Surface
    ) {
        APrimaryButton(
            onClick = onGetCarClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = ATheme.dimens.ScreenPaddingHorizontal,
                    end = ATheme.dimens.ScreenPaddingHorizontal,
                    top = ATheme.dimens.SpacingMd,
                    bottom = ATheme.dimens.SpacingMd + navigationBarPadding
                        .calculateBottomPadding()
                )
        ) {
            Icon(
                imageVector = Icons.Filled.DirectionsCar,
                contentDescription = null,
                modifier = Modifier.size(ICON_SIZE)
            )
            Spacer(modifier = Modifier.size(ATheme.dimens.SpacingSm))
            Text(
                text = stringResource(Res.string.car_details_get_car),
                fontWeight = FontWeight.SemiBold,
                fontSize = BUTTON_FONT_SIZE
            )
        }
    }
}
