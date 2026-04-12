package com.ae.alice.presentation.screens.main.components

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
import androidx.compose.runtime.Composable
import com.ae.alice.designsystem.components.bottomNavigation.BottomNavigationBar
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainBottomNavBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    BottomNavigationBar(selectedItemIndex = selectedTab) {
        bottomNavigationItem(
            selectedIcon = painterResource(Res.drawable.ic_home_selected),
            notSelectedIcon = painterResource(Res.drawable.ic_home),
            title = stringResource(Res.string.nav_home_ar),
            entry = { onTabSelected(0) }
        )
        bottomNavigationItem(
            selectedIcon = painterResource(Res.drawable.ic_bookmark_selected),
            notSelectedIcon = painterResource(Res.drawable.ic_bookmark),
            title = stringResource(Res.string.nav_archive_ar),
            entry = { onTabSelected(1) }
        )
        bottomNavigationItem(
            selectedIcon = painterResource(Res.drawable.ic_profile_selected),
            notSelectedIcon = painterResource(Res.drawable.ic_profile),
            title = stringResource(Res.string.nav_profile_ar),
            entry = { onTabSelected(2) }
        )
    }
}
