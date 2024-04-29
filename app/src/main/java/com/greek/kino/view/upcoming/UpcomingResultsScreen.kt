package com.greek.kino.view.upcoming

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.greek.kino.R
import com.greek.kino.base.components.HeaderText
import com.greek.kino.base.components.RegularText
import com.greek.kino.domain.extensions.TIMER_REFRESH_TIME
import com.greek.kino.view.navigation.NavItem
import com.greek.kino.viewModel.ResultsViewModel
import kotlinx.coroutines.delay

@Composable
fun UpcomingResultsScreen(
    viewModel: ResultsViewModel,
    navController: NavController,
) {
    val upcomingGames by viewModel.upcomingGameResults.collectAsState(initial = emptyList())
    var workCompleted by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (!workCompleted) {
            viewModel.loadUpcomingGames()
            workCompleted = true
        }
    }

    Column(
        modifier =
            Modifier.fillMaxSize(),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            HeaderText(text = stringResource(id = R.string.draw_time))
            HeaderText(text = stringResource(id = R.string.draw_remaining))
        }

        LazyColumn(
            modifier =
                Modifier
                    .padding(horizontal = 2.dp),
        ) {
            items(items = upcomingGames, itemContent = { item ->

                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .background(Color.DarkGray)
                            .clickable {
                                navController.navigate(
                                    NavItem.Talon.route.replace(
                                        oldValue = "{drawId}",
                                        newValue = "${item.drawId ?: viewModel.selectedDraw}",
                                    ),
                                ) {
                                }
                            }
                            .padding(horizontal = 32.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    var remainingTime by rememberSaveable { mutableStateOf("") }

                    LaunchedEffect(Unit) {
                        while (true) {
                            remainingTime = item.getRemainingTime()
                            delay(TIMER_REFRESH_TIME)
                        }
                    }
                    RegularText(text = item.drawTimeFormatted)
                    RegularText(text = remainingTime)
                }
            })
        }
    }
}
