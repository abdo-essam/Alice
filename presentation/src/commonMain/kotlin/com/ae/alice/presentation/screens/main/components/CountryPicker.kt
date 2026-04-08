package com.ae.alice.presentation.screens.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.places_confirm
import alice.presentation.generated.resources.pick_your_country
import com.ae.alice.designsystem.components.bottomSheet.BottomSheet
import com.ae.alice.designsystem.components.button.PrimaryButton
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme
import com.ae.alice.domain.entity.Country
import org.jetbrains.compose.resources.stringResource

import com.ae.alice.designsystem.components.scaffold.ScaffoldScope

@Composable
fun ScaffoldScope.CountryPicker(
    isVisible: Boolean,
    currentCountry: Country,
    onDismiss: () -> Unit,
    onClickConfirm: (Country) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedCountry by remember(currentCountry) { mutableStateOf(currentCountry) }

    BottomSheet(
        isVisible = isVisible,
        onDismissRequest = onDismiss,
        modifier = modifier,
        sheetContent = {
            Text(
                text = stringResource(Res.string.pick_your_country),
                color = Theme.colorScheme.shadePrimary,
                style = Theme.typography.title.small,
                modifier = Modifier.padding(
                    horizontal = Theme.spacing._16,
                    vertical = Theme.spacing._24
                )
            )

            LazyColumn(
                contentPadding = PaddingValues(bottom = 72.dp),
                verticalArrangement = Arrangement.spacedBy(Theme.spacing._8),
                modifier = Modifier.padding(horizontal = Theme.spacing._16)
            ) {
                items(
                    items = Country.entries,
                    key = { it.name }
                ) { country ->
                    CountrySelectableRowItem(
                        country = country,
                        isSelected = country == selectedCountry,
                        onClick = { selectedCountry = country },
                    )
                }
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Theme.colorScheme.background.surface)
                    .padding(horizontal = Theme.spacing._16, vertical = Theme.spacing._12)
            ) {
                PrimaryButton(
                    text = stringResource(Res.string.places_confirm),
                    isEnabled = currentCountry != selectedCountry,
                    onClick = { onClickConfirm(selectedCountry) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}
