package com.ae.alice.presentation.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.ic_bookmark
import alice.presentation.generated.resources.ic_bookmark_selected
import alice.presentation.generated.resources.ic_home
import alice.presentation.generated.resources.ic_home_selected
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
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainScreen(
    onBrandClick: (Brand) -> Unit,
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Drawer", modifier = Modifier.padding(16.dp))
                // Add more drawer items here if needed
            }
        }
    ) {
        Scaffold(
            backgroundColor = Theme.colorScheme.background.surfaceLow,
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
