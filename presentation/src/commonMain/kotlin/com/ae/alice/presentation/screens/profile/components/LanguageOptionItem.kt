package com.ae.alice.presentation.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.ae.alice.designsystem.components.radioButton.RadioButton
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme
import com.ae.alice.designsystem.locale.AppLanguage

@Composable
fun LanguageOptionItem(
    isSelected: Boolean,
    selectedAppLanguage: AppLanguage,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Theme.radius.lg))
            .background(
                color = Theme.colorScheme.primary.primary.copy(alpha = 0.08f),
                shape = RoundedCornerShape(Theme.radius.lg)
            )
            .clickable(
                enabled = !isSelected,
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(horizontal = Theme.spacing._16, vertical = Theme.spacing._12)
    ) {
        Text(
            text = selectedAppLanguage.displayName,
            color = Theme.colorScheme.primary.primary,
            style = Theme.typography.title.small,
        )
        RadioButton(
            isSelected = isSelected, onClick = null
        )
    }
}
