package com.ae.alice.presentation.screens.main.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.radioButton.RadioButton
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme
import com.ae.alice.domain.entity.Country

@Composable
fun CountrySelectableRowItem(
    country: Country,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val animatedCountryItemColor by animateColorAsState(
        targetValue = if (isSelected) Theme.colorScheme.background.surfaceHigh
        else Theme.colorScheme.background.surfaceLow
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = animatedCountryItemColor,
                shape = RoundedCornerShape(Theme.radius.lg)
            )
            .clip(RoundedCornerShape(Theme.radius.lg))
            .clickable(
                enabled = !isSelected,
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(horizontal = Theme.spacing._16, vertical = Theme.spacing._12)
    ) {
        Text(
            text = country.flagEmoji,
            style = Theme.typography.title.large,
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = Theme.spacing._8)
        ) {
            Text(
                text = country.countryName,
                color = Theme.colorScheme.primary.primary,
                style = Theme.typography.title.small,
            )
        }

        RadioButton(
            isSelected = isSelected,
            onClick = null
        )
    }
}
