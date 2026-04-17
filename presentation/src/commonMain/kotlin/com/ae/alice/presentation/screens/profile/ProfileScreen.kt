package com.ae.alice.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.ic_addresses
import alice.presentation.generated.resources.ic_contact_us
import alice.presentation.generated.resources.ic_edit_profile_info
import alice.presentation.generated.resources.ic_language
import alice.presentation.generated.resources.ic_logout
import alice.presentation.generated.resources.ic_password_lock
import alice.presentation.generated.resources.ic_privacy_and_policies
import alice.presentation.generated.resources.ic_theme
import alice.presentation.generated.resources.profile_account_settings
import alice.presentation.generated.resources.profile_addresses
import alice.presentation.generated.resources.profile_app_settings
import alice.presentation.generated.resources.profile_change_password
import alice.presentation.generated.resources.profile_contact_us
import alice.presentation.generated.resources.profile_edit_profile
import alice.presentation.generated.resources.profile_language
import alice.presentation.generated.resources.profile_logout
import alice.presentation.generated.resources.profile_other
import alice.presentation.generated.resources.profile_privacy_policy
import alice.presentation.generated.resources.profile_theme
import alice.presentation.generated.resources.profile_title
import alice.presentation.generated.resources.profile_version
import alice.presentation.generated.resources.share_app_message
import alice.presentation.generated.resources.share_app_title
import com.ae.alice.designsystem.components.appBar.AppBar
import com.ae.alice.designsystem.components.scaffold.Scaffold
import com.ae.alice.designsystem.components.settings.SettingItem
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.locale.LocalAppLocale
import com.ae.alice.designsystem.theme.Theme
import com.ae.alice.presentation.screens.profile.components.LanguageDialog
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

/**
 * Profile screen — matches MENA's profile layout:
 * - Profile image + name + username at top
 * - Account Settings section (edit profile, change password, addresses)
 * - App Settings section (language, theme)
 * - Other section (privacy, contact us)
 * - Logout + version at bottom
 */
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    var showShareSheet by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val localeState = LocalAppLocale.current
    var isLanguageDialogOpen by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(false) }
    var selectedLanguage by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(localeState.language) }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is ProfileEffect.SwitchLanguage -> isLanguageDialogOpen = true
                else -> {}
            }
        }
    }

    Scaffold(
        backgroundColor = Theme.colorScheme.background.surface,
        topBar = {
            AppBar(title = stringResource(Res.string.profile_title))
        },
        overlays = {
            dialog(isVisible = isLanguageDialogOpen) { isVisible ->
                LanguageDialog(
                    appLanguages = com.ae.alice.designsystem.locale.AppLanguage.entries,
                    isVisible = isVisible,
                    currentAppLanguage = localeState.language,
                    selectedAppLanguage = selectedLanguage,
                    onDismissRequest = { isLanguageDialogOpen = false },
                    onConfirmLanguageSelection = {
                        localeState.setLanguage(selectedLanguage)
                        isLanguageDialogOpen = false
                    },
                    onLanguageChanged = { selectedLanguage = it }
                )
            }
        }
    ) {
        if (showShareSheet) {
            com.ae.alice.presentation.utils.ShareSheet(
                title = stringResource(Res.string.share_app_title),
                message = stringResource(Res.string.share_app_message),
                shareLink = "https://play.google.com/store/apps/details?id=com.ae.alice",
                onDismiss = { showShareSheet = false }
            )
        }
        LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = Theme.spacing._16),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile avatar + info
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = Theme.spacing._16)
                    ) {
                        // Avatar placeholder
                        Box {
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(CircleShape)
                                    .background(Theme.colorScheme.background.surfaceHigh)
                                    .border(2.dp, Theme.colorScheme.stroke, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = state.fullName.take(2).uppercase(),
                                    style = Theme.typography.title.large,
                                    color = Theme.colorScheme.primary.primary
                                )
                            }
                            
                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(end = 4.dp, bottom = 4.dp)
                                    .size(16.dp)
                                    .border(2.dp, Theme.colorScheme.background.surface, CircleShape)
                                    .background(Theme.colorScheme.success, CircleShape)
                            )
                        }

                        Text(
                            text = state.fullName,
                            style = Theme.typography.label.medium,
                            color = Theme.colorScheme.shadePrimary,
                            modifier = Modifier.padding(top = Theme.spacing._8)
                        )
                        Text(
                            text = "@${state.username}",
                            style = Theme.typography.label.small,
                            color = Theme.colorScheme.shadeSecondary,
                            modifier = Modifier.padding(top = Theme.spacing._2)
                        )
                        com.ae.alice.presentation.screens.profile.components.InviteFriendsCard(
                            onClick = { showShareSheet = true }
                        )
                    }
                }

                // Account Settings
                item {
                    SettingsSection(title = stringResource(Res.string.profile_account_settings)) {
                        SettingItem(
                            title = stringResource(Res.string.profile_edit_profile),
                            leadingIcon = painterResource(Res.drawable.ic_edit_profile_info),
                            onClick = { scope.launch { viewModel.processIntent(ProfileIntent.EditProfile) } }
                        )
                        SettingItem(
                            title = stringResource(Res.string.profile_change_password),
                            leadingIcon = painterResource(Res.drawable.ic_password_lock),
                            onClick = { scope.launch { viewModel.processIntent(ProfileIntent.ChangePassword) } }
                        )
                    }
                }

                // App Settings
                item {
                    SettingsSection(title = stringResource(Res.string.profile_app_settings)) {
                        SettingItem(
                            title = stringResource(Res.string.profile_language),
                            leadingIcon = painterResource(Res.drawable.ic_language),
                            onClick = { scope.launch { viewModel.processIntent(ProfileIntent.ChangeLanguage) } },
                            trailingText = localeState.language.displayName
                        )
                        SettingItem(
                            title = stringResource(Res.string.profile_theme),
                            leadingIcon = painterResource(Res.drawable.ic_theme),
                            onClick = { scope.launch { viewModel.processIntent(ProfileIntent.ChangeTheme) } }
                        )
                    }
                }

                // Other
                item {
                    SettingsSection(title = stringResource(Res.string.profile_other)) {
                        SettingItem(
                            title = stringResource(Res.string.profile_privacy_policy),
                            leadingIcon = painterResource(Res.drawable.ic_privacy_and_policies),
                            onClick = { scope.launch { viewModel.processIntent(ProfileIntent.PrivacyPolicy) } }
                        )
                        SettingItem(
                            title = stringResource(Res.string.profile_contact_us),
                            leadingIcon = painterResource(Res.drawable.ic_contact_us),
                            onClick = { scope.launch { viewModel.processIntent(ProfileIntent.ContactUs) } }
                        )
                        SettingItem(
                            title = stringResource(Res.string.profile_logout),
                            leadingIcon = painterResource(Res.drawable.ic_logout),
                            onClick = { scope.launch { viewModel.processIntent(ProfileIntent.Logout) } }
                        )
                    }
                }

                // Version
                item {
                    Text(
                        text = "${stringResource(Res.string.profile_version)} ${state.versionNumber}",
                        style = Theme.typography.label.small,
                        color = Theme.colorScheme.shadeSecondary,
                        modifier = Modifier.padding(vertical = Theme.spacing._16)
                    )
                }
            }
    }
}

/**
 * Section header + grouped settings.
 */
@Composable
private fun SettingsSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.padding(top = Theme.spacing._24),
        verticalArrangement = Arrangement.spacedBy(Theme.spacing._8)
    ) {
        Text(
            text = title,
            style = Theme.typography.label.medium,
            color = Theme.colorScheme.shadeSecondary,
        )
        content()
    }
}
