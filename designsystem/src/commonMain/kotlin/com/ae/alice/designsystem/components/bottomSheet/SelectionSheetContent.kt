package com.ae.alice.designsystem.components.bottomSheet

import alice.designsystem.generated.resources.Res
import alice.designsystem.generated.resources.no_results
import alice.designsystem.generated.resources.search_placeholder
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.components.textfield.SearchField
import com.ae.alice.designsystem.theme.Theme
import org.jetbrains.compose.resources.stringResource

@Composable
fun SelectionSheetContent(
    title: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    searchPlaceholder: String = stringResource(Res.string.search_placeholder),
    showSearch: Boolean = true,
) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredOptions = remember(searchQuery, options) {
        if (searchQuery.isBlank()) options
        else options.filter { it.contains(searchQuery, ignoreCase = true) }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Theme.spacing._16)
            .padding(bottom = Theme.spacing._24),
    ) {
        Spacer(modifier = Modifier.height(Theme.spacing._8))

        // Title
        Text(
            text = title,
            style = Theme.typography.title.medium,
            color = Theme.colorScheme.shadePrimary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Theme.spacing._12),
        )

        // Search field
        if (showSearch) {
            SearchField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = searchPlaceholder,
                onClear = { searchQuery = "" },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = Theme.spacing._12),
            )
        }

        // Options list
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 400.dp)
        ) {
            if (filteredOptions.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = Theme.spacing._24),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = stringResource(Res.string.no_results),
                            style = Theme.typography.body.medium,
                            color = Theme.colorScheme.shadeTertiary,
                        )
                    }
                }
            } else {
                items(filteredOptions) { option ->
                    SelectionItem(
                        text = option,
                        isSelected = option == selectedOption,
                        onClick = { onOptionSelected(option) },
                    )
                }
            }
        }
    }
}

@Composable
private fun SelectionItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Theme.spacing._2)
            .clip(RoundedCornerShape(Theme.radius.md))
            .background(
                if (isSelected) Theme.colorScheme.brand.brand.copy(alpha = 0.08f)
                else Theme.colorScheme.background.surface
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() }
            .padding(
                horizontal = Theme.spacing._16,
                vertical = Theme.spacing._12
            ),
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(
            text = text,
            style = if (isSelected) Theme.typography.label.large
            else Theme.typography.body.medium,
            color = if (isSelected) Theme.colorScheme.brand.brand
            else Theme.colorScheme.shadePrimary,
        )
    }
}
