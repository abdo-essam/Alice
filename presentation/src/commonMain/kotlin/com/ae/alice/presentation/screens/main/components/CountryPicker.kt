package com.ae.alice.presentation.screens.main.components

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.pick_your_country
import alice.presentation.generated.resources.selector_search_country_placeholder
import com.ae.alice.designsystem.components.bottomSheet.BottomSheet
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.components.textfield.SearchField
import com.ae.alice.designsystem.theme.Theme
import com.ae.alice.domain.entity.Country
import org.jetbrains.compose.resources.stringResource
import com.ae.alice.designsystem.components.scaffold.ScaffoldScope

@Composable
fun ScaffoldScope.CountryPicker(
    isVisible: Boolean,
    countries: List<Country>,
    currentCountry: Country,
    onDismiss: () -> Unit,
    onClickConfirm: (Country) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredCountries = remember(searchQuery, countries) {
        countries.filter {
            it.countryName.contains(searchQuery, ignoreCase = true) ||
            it.countryCodeName.contains(searchQuery, ignoreCase = true)
        }
    }

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
                    start = Theme.spacing._16,
                    end = Theme.spacing._16,
                    top = Theme.spacing._24,
                    bottom = Theme.spacing._12
                )
            )

            SearchField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                onClear = { searchQuery = "" },
                placeholder = stringResource(Res.string.selector_search_country_placeholder),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Theme.spacing._16, vertical = Theme.spacing._8)
            )

            LazyColumn(
                contentPadding = PaddingValues(bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(Theme.spacing._8),
                modifier = Modifier.padding(
                    horizontal = Theme.spacing._16,
                    vertical = Theme.spacing._8
                )
            ) {
                items(
                    items = filteredCountries,
                    key = { it.id }
                ) { country ->
                    CountrySelectableRowItem(
                        country = country,
                        isSelected = country == currentCountry,
                        onClick = { 
                            onClickConfirm(country)
                            onDismiss()
                        },
                    )
                }
            }
        }
    )
}
