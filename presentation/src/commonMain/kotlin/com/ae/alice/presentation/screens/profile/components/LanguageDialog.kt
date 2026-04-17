package com.ae.alice.presentation.screens.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.places_save
import alice.presentation.generated.resources.profile_language
import com.ae.alice.designsystem.components.button.PrimaryButton
import com.ae.alice.designsystem.components.dialog.BasicDialog
import com.ae.alice.designsystem.components.scaffold.ScaffoldScope
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme
import com.ae.alice.designsystem.locale.AppLanguage
import org.jetbrains.compose.resources.stringResource

@Composable
fun ScaffoldScope.LanguageDialog(
    appLanguages: List<AppLanguage>,
    isVisible: Boolean,
    currentAppLanguage: AppLanguage,
    selectedAppLanguage: AppLanguage,
    onDismissRequest: () -> Unit,
    onConfirmLanguageSelection: () -> Unit,
    onLanguageChanged: (AppLanguage) -> Unit,
) {
    if (!isVisible) return
    BasicDialog(
        isVisible = isVisible,
        onDismiss = onDismissRequest,
        onCancelClick = onDismissRequest
    ) {
        Box(
            contentAlignment = Alignment.TopCenter,
        ) {
            LazyColumn(
                modifier = Modifier.padding(vertical = Theme.spacing._12),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Theme.spacing._4)
            ) {
                item {
                    Text(
                        modifier = Modifier.padding(bottom = 20.dp),
                        text = stringResource(Res.string.profile_language),
                        color = Theme.colorScheme.shadePrimary,
                        style = Theme.typography.title.small,
                    )
                }
                items(appLanguages, key = { it.code }) {
                    LanguageOptionItem(
                        isSelected = it == selectedAppLanguage,
                        selectedAppLanguage = it,
                        onClick = {
                            onLanguageChanged(it)
                        }
                    )
                }
                item {
                    PrimaryButton(
                        text = stringResource(Res.string.places_save),
                        isEnabled = selectedAppLanguage != currentAppLanguage,
                        onClick = onConfirmLanguageSelection,
                        modifier = Modifier.padding(top = 20.dp).fillMaxWidth()
                    )
                }
            }
        }
    }
}
