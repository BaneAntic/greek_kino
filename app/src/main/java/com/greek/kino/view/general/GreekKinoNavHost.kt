package com.greek.kino.view.general

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.greek.kino.base.theme.Background
import com.greek.kino.base.theme.GreekKinoTheme
import com.greek.kino.view.liveResult.LiveResultsScreen
import com.greek.kino.view.navigation.GreekKinoNavigationBar
import com.greek.kino.view.navigation.NavItem
import com.greek.kino.view.results.ResultsScreen
import com.greek.kino.view.talon.TalonScreen
import com.greek.kino.view.upcoming.UpcomingResultsScreen
import com.greek.kino.viewModel.ResultsViewModel

@Composable
fun GreekKinoContent(viewModel: ResultsViewModel) =
    GreekKinoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Background,
        ) {
            val navController = rememberNavController()
            val topBarState = rememberSaveable { (mutableStateOf(false)) }

            Scaffold(
                topBar = {
                    GreekKinoNavigationBar(navController, topBarState)
                },
            ) {
                Box(
                    modifier =
                        Modifier
                            .padding(
                                PaddingValues(
                                    0.dp,
                                    it.calculateTopPadding(),
                                    0.dp,
                                    it.calculateBottomPadding(),
                                ),
                            )
                            .background(Background),
                ) {
                    NavHost(
                        navController,
                        startDestination = NavItem.UpcomingGames.route,
                    ) {
                        composable(NavItem.UpcomingGames.route) {
                            topBarState.value = false
                            UpcomingResultsScreen(viewModel, navController)
                        }
                        composable(NavItem.Talon.route) {
                            topBarState.value = true
                            val drawId =
                                navController.currentBackStackEntry?.arguments?.getString("drawId")
                                    ?: ""
                            TalonScreen(
                                viewModel = viewModel,
                                drawId = drawId,
                                navController = navController,
                            )
                        }
                        composable(NavItem.Live.route) {
                            topBarState.value = true
                            LiveResultsScreen()
                        }
                        composable(NavItem.Results.route) {
                            topBarState.value = true
                            ResultsScreen(viewModel)
                        }
                    }
                }
            }
        }
    }
