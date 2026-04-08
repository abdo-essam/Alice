package com.ae.alice.presentation.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.setValue
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.ic_bookmark
import alice.presentation.generated.resources.ic_bookmark_selected
import alice.presentation.generated.resources.ic_home
import alice.presentation.generated.resources.ic_home_selected
import alice.presentation.generated.resources.ic_location
import alice.presentation.generated.resources.ic_profile
import alice.presentation.generated.resources.ic_profile_selected
import alice.presentation.generated.resources.nav_archive_ar
import alice.presentation.generated.resources.nav_home_ar
import alice.presentation.generated.resources.nav_profile_ar
import com.ae.alice.designsystem.components.appBar.HomeAppBar
import com.ae.alice.designsystem.components.bottomNavigation.BottomNavigationBar
import com.ae.alice.designsystem.components.scaffold.Scaffold
import com.ae.alice.designsystem.theme.Theme
import com.ae.alice.domain.entity.Brand
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import com.ae.alice.presentation.screens.main.components.CountryPicker
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainScreen(
    onBrandClick: (Brand) -> Unit,
    viewModel: MainViewModel = org.koin.compose.viewmodel.koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    var selectedTab by remember { mutableIntStateOf(0) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "Alice App",
                    style = Theme.typography.title.large,
                    modifier = Modifier.padding(16.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Home") },
                    selected = false,
                    onClick = { scope.launch { drawerState.close() } }
                )
                NavigationDrawerItem(
                    label = { Text("Categories") },
                    selected = false,
                    onClick = { scope.launch { drawerState.close() } }
                )
                NavigationDrawerItem(
                    label = { Text("Settings") },
                    selected = false,
                    onClick = { scope.launch { drawerState.close() } }
                )
            }
        }
    ) {
        Scaffold(
            backgroundColor = when (selectedTab) {
                2 -> Theme.colorScheme.background.surface
                else -> Theme.colorScheme.background.surfaceLow
            },
            topBar = {
                when (selectedTab) {
                    0 -> HomeAppBar(
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Menu",
                                    tint = Theme.colorScheme.shadePrimary
                                )
                            }
                        },
                        trailingContent = {
                            Row(
                                modifier = Modifier
                                    .background(
                                        color = Theme.colorScheme.background.surfaceLow,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .clickable(
                                        interactionSource = androidx.compose.runtime.remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                                        indication = null
                                    ) {
                                        viewModel.processIntent(MainIntent.ShowCountryPicker)
                                    }
                                    .padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                com.ae.alice.designsystem.components.text.Text(
                                    text = state.selectedCountry.flagEmoji
                                )
                                com.ae.alice.designsystem.components.text.Text(
                                    text = state.selectedCountry.countryName,
                                    style = Theme.typography.label.large,
                                    color = Theme.colorScheme.shadePrimary
                                )
                                androidx.compose.material3.Icon(
                                    imageVector = androidx.compose.material.icons.Icons.Default.KeyboardArrowDown,
                                    contentDescription = "Select country",
                                    tint = Theme.colorScheme.shadePrimary,
                                    modifier = Modifier.padding(start = 2.dp)
                                )
                            }
                        }
                    )
                // Archive and Profile have their own app bars inside their content
            }
        },
        bottomBar = {
            BottomNavigationBar(selectedItemIndex = selectedTab) {
                bottomNavigationItem(
                    selectedIcon = painterResource(Res.drawable.ic_home_selected),
                    notSelectedIcon = painterResource(Res.drawable.ic_home),
                    title = stringResource(Res.string.nav_home_ar),
                    entry = { selectedTab = 0 }
                )
                bottomNavigationItem(
                    selectedIcon = painterResource(Res.drawable.ic_bookmark_selected),
                    notSelectedIcon = painterResource(Res.drawable.ic_bookmark),
                    title = stringResource(Res.string.nav_archive_ar),
                    entry = { selectedTab = 1 }
                )
                bottomNavigationItem(
                    selectedIcon = painterResource(Res.drawable.ic_profile_selected),
                    notSelectedIcon = painterResource(Res.drawable.ic_profile),
                    title = stringResource(Res.string.nav_profile_ar),
                    entry = { selectedTab = 2 }
                )
            }
        },
        overlays = {
            bottomSheet(isVisible = state.showCountryPicker) { overlayVisible ->
                CountryPicker(
                    isVisible = overlayVisible,
                    countries = state.countries,
                    currentCountry = state.selectedCountry,
                    onDismiss = { viewModel.processIntent(MainIntent.HideCountryPicker) },
                    onClickConfirm = { country: com.ae.alice.domain.entity.Country ->
                        viewModel.processIntent(MainIntent.SelectCountry(country))
                    }
                )
            }
        }
    ) {
        when (selectedTab) {
            0 -> HomeBrandsTab(onBrandClick = onBrandClick)
            1 -> ArchiveTab()
            2 -> ProfileTab()
        }
    }
}
}
