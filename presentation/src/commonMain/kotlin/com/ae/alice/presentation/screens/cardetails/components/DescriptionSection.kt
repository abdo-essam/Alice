package com.ae.alice.presentation.screens.cardetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ae.alice.designsystem.theme.ATheme
import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.car_details_about
import org.jetbrains.compose.resources.stringResource

private val DESCRIPTION_LINE_HEIGHT = 22.sp

@Composable
fun DescriptionSection(
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = ATheme.dimens.ScreenPaddingHorizontal),
        verticalArrangement = Arrangement.spacedBy(ATheme.dimens.SpacingSm)
    ) {
        Text(
            text = stringResource(Res.string.car_details_about),
            style = ATheme.typography.TitleMedium,
            fontWeight = FontWeight.SemiBold,
            color = ATheme.colors.Light.TextPrimary
        )
        Text(
            text = description,
            style = ATheme.typography.BodyMedium,
            color = ATheme.colors.Light.TextSecondary,
            lineHeight = DESCRIPTION_LINE_HEIGHT
        )
    }
}
