package com.ae.alice.presentation.screens.cardetails.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ae.alice.designsystem.components.dropdown.DropdownSelector
import com.ae.alice.designsystem.theme.Theme
import com.ae.alice.domain.entity.CarModel

@Composable
fun CarDetailsContent(
    model: CarModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HeroImageSection(
            imageUrl = model.imageUrl,
            contentDescription = model.name
        )

        Spacer(modifier = Modifier.height(Theme.spacing._16))

        // Options
        var selectedYear by remember { mutableStateOf(model.year?.toString() ?: "2024") }
        var selectedCategory by remember { mutableStateOf(model.category ?: "Sedan") }
        var selectedEngine by remember { mutableStateOf(model.engineType ?: "V6") }

        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = Theme.spacing._16)
        ) {
            DropdownSelector(
                label = "Year",
                selectedValue = selectedYear,
                options = listOf("2024", "2025", "2026"),
                onOptionSelected = { selectedYear = it },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(Theme.spacing._12))
            DropdownSelector(
                label = "Category",
                selectedValue = selectedCategory,
                options = listOf("Sedan", "SUV", "Coupe", "Truck"),
                onOptionSelected = { selectedCategory = it },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(Theme.spacing._12))
            DropdownSelector(
                label = "Engine Make",
                selectedValue = selectedEngine,
                options = listOf("V6", "V8", "Electric", "Hybrid"),
                onOptionSelected = { selectedEngine = it },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(Theme.spacing._16))

        model.description?.let { description ->
            Spacer(modifier = Modifier.height(Theme.spacing._20))

            SectionDivider()

            Spacer(modifier = Modifier.height(Theme.spacing._20))

            DescriptionSection(description = description)
        }

        Spacer(modifier = Modifier.height(Theme.spacing._40))
    }
}
