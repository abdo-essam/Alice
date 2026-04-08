package com.ae.alice.presentation.screens.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme
import com.ae.alice.domain.entity.Country
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check

@Composable
fun CountrySelectableRowItem(
    country: Country,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(
                if (isSelected) Theme.colorScheme.background.surfaceHigh
                else Theme.colorScheme.background.surface
            )
            .clickable(
                interactionSource = androidx.compose.runtime.remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(vertical = Theme.spacing._12, horizontal = Theme.spacing._16),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = country.flagEmoji,
            style = Theme.typography.title.large,
        )

        Text(
            text = country.countryName,
            style = Theme.typography.body.medium,
            color = if (isSelected) Theme.colorScheme.primary.primary else Theme.colorScheme.shadePrimary,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start
        )

        if (isSelected) {
            androidx.compose.material3.Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.Check,
                contentDescription = "Selected",
                tint = Theme.colorScheme.primary.primary
            )
        }
    }
}
