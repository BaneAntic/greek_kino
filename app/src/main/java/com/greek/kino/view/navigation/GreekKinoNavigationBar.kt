package com.greek.kino.view.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.greek.kino.base.components.NavigationText
import com.greek.kino.base.theme.PrimaryBlue
import com.greek.kino.base.theme.PrimaryOrange
import com.greek.kino.base.theme.White

@Suppress("ktlint:standard:function-naming")
@Composable
fun GreekKinoNavigationBar(
    navController: NavController,
    bottomBarState: MutableState<Boolean>,
) {
    AnimatedVisibility(visible = bottomBarState.value) {
        NavigationBar(containerColor = PrimaryBlue) {
            NavItem.allValues.forEach { item ->
                AddNavItem(
                    navController = navController,
                    screen = item,
                )
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun RowScope.AddNavItem(
    navController: NavController,
    screen: NavItem,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBarItem(
        // Text that shows bellow the icon
        label = {
            NavigationText(
                text = stringResource(id = screen.title),
                isSelected = currentRoute == screen.route,
            )
        },
        icon = {
            Icon(
                painterResource(id = screen.icon),
                contentDescription = stringResource(id = screen.title),
                tint = if (currentRoute == screen.route) PrimaryOrange else White,
            )
        },
        selected = currentRoute == screen.route,
        alwaysShowLabel = true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        },
        colors =
            NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Transparent,
                indicatorColor = Color.Transparent,
            ),
    )
}
