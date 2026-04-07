package com.ae.alice.presentation.screens.cardetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme
import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.car_details_about
import org.jetbrains.compose.resources.stringResource

@Composable
fun DescriptionSection(
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = Theme.spacing._16),
        verticalArrangement = Arrangement.spacedBy(Theme.spacing._8)
    ) {
        Text(
            text = stringResource(Res.string.car_details_about),
            style = Theme.typography.title.medium,
            color = Theme.colorScheme.shadePrimary
        )
        Text(
            text = description,
            style = Theme.typography.body.medium,
            color = Theme.colorScheme.shadeSecondary,
            maxLines = Int.MAX_VALUE
        )
    }
}
