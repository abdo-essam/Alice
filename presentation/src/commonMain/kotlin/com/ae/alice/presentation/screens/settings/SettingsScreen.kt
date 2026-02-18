package com.ae.alice.presentation.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import alice.designsystem.generated.resources.Res
import alice.designsystem.generated.resources.language_arabic
import alice.designsystem.generated.resources.language_english
import alice.designsystem.generated.resources.language_title
import alice.designsystem.generated.resources.settings_title
import alice.designsystem.generated.resources.theme_dark
import alice.designsystem.generated.resources.theme_light
import alice.designsystem.generated.resources.theme_system
import alice.designsystem.generated.resources.theme_title
import com.ae.alice.designsystem.components.AHeader
import com.ae.alice.designsystem.components.button.ARadioButton
import com.ae.alice.designsystem.components.AText
import com.ae.alice.designsystem.components.scaffold.AScaffold
import com.ae.alice.designsystem.theme.ATheme
import com.ae.alice.domain.model.AppLanguage
import com.ae.alice.domain.model.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    viewModel: SettingsViewModel = koinViewModel()
) {
    val currentTheme by viewModel.currentTheme.collectAsState()
    val currentLanguage by viewModel.currentLanguage.collectAsState()

    AScaffold(
        backgroundColor = ATheme.colorScheme.background.surface,
        topBar = {
            AHeader(
                title = stringResource(Res.string.settings_title),
                showBackIcon = true,
                onBackClick = onBackClick
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(ATheme.dimens.screenPaddingHorizontal),
            verticalArrangement = Arrangement.spacedBy(ATheme.dimens.spacingLg)
        ) {
            // Theme Section
            SettingsSection(title = stringResource(Res.string.theme_title)) {
                ThemeOption(
                    text = stringResource(Res.string.theme_system),
                    selected = currentTheme == AppTheme.SYSTEM,
                    onClick = { viewModel.onThemeChanged(AppTheme.SYSTEM) }
                )
                ThemeOption(
                    text = stringResource(Res.string.theme_light),
                    selected = currentTheme == AppTheme.LIGHT,
                    onClick = { viewModel.onThemeChanged(AppTheme.LIGHT) }
                )
                ThemeOption(
                    text = stringResource(Res.string.theme_dark),
                    selected = currentTheme == AppTheme.DARK,
                    onClick = { viewModel.onThemeChanged(AppTheme.DARK) }
                )
            }

            // Language Section
            SettingsSection(title = stringResource(Res.string.language_title)) {
                ThemeOption(
                    text = stringResource(Res.string.language_english),
                    selected = currentLanguage == AppLanguage.English,
                    onClick = { viewModel.onLanguageChanged(AppLanguage.English) }
                )
                ThemeOption(
                    text = stringResource(Res.string.language_arabic),
                    selected = currentLanguage == AppLanguage.Arabic,
                    onClick = { viewModel.onLanguageChanged(AppLanguage.Arabic) }
                )
            }
        }
    }
}

@Composable
private fun SettingsSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column {
        AText(
            text = title,
            style = ATheme.typo.title.large,
            color = ATheme.colorScheme.brand.brand
        )
        Spacer(modifier = Modifier.height(ATheme.dimens.spacingSm))
        content()
    }
}

@Composable
private fun ThemeOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = ATheme.dimens.spacingSm),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ARadioButton(
            isSelected = selected,
            onClick = onClick
        )
        AText(
            text = text,
            style = ATheme.typo.body.medium,
            color = ATheme.colorScheme.shadePrimary,
            modifier = Modifier.padding(start = ATheme.dimens.spacingSm)
        )
    }
}
