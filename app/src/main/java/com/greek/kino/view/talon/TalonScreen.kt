package com.greek.kino.view.talon

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.greek.kino.R
import com.greek.kino.base.components.ButtonText
import com.greek.kino.base.components.ComposeToast
import com.greek.kino.base.components.DropDownMenu
import com.greek.kino.base.components.RegularText
import com.greek.kino.base.components.RoundedCornerButton
import com.greek.kino.base.components.RoundedTextView
import com.greek.kino.base.components.SubHeaderText
import com.greek.kino.base.theme.White
import com.greek.kino.data.util.GameConstants
import com.greek.kino.data.util.GameConstants.GreekKinoNumberOfCombinations
import com.greek.kino.data.util.GameConstants.GreekKinoTalon
import com.greek.kino.domain.extensions.MIN_REMAINING_TIME
import com.greek.kino.domain.extensions.TIMER_REFRESH_TIME
import com.greek.kino.view.general.LazyKinoBoard
import com.greek.kino.view.navigation.NavItem
import com.greek.kino.viewModel.ResultsViewModel
import kotlinx.coroutines.delay

@Composable
fun TalonScreen(
    viewModel: ResultsViewModel,
    drawId: String,
    navController: NavController,
) {
    val gameDetails by viewModel.gameDetailsResult.collectAsState(initial = null)
    val oddsList = GameConstants.GreekKinoOdds
    val selectedNumberCount by
        viewModel.selectedNumberCount.collectAsState()
    val selectedNumbers by viewModel.selectedNumbers.collectAsState(emptyList())
    var showToast by remember { mutableStateOf(false) }
    var toastMessageId by remember { mutableStateOf(0) }

    var workCompleted by rememberSaveable { mutableStateOf(false) }
    var remainingTime by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(Unit) {
        if (!workCompleted) {
            viewModel.clearSelectedValues()
            viewModel.loadGameDetailsFor(drawId.toIntOrNull())
            workCompleted = true
        }
        while (true) {
            remainingTime = gameDetails?.getRemainingTimeAsString() ?: ""
            delay(TIMER_REFRESH_TIME)
        }
    }

    Box(
        modifier =
            Modifier
                .padding(vertical = 16.dp)
                .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd,
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
        ) {
            gameDetails?.let {
                SubHeaderText(
                    text =
                        stringResource(
                            id = R.string.draw_time_leg,
                            it.drawTimeFormatted,
                            it.drawId.toString(),
                        ),
                    modifier =
                        Modifier.clickable {
                            navController.navigate(NavItem.UpcomingGames.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        },
                )
            }
            Row(
                Modifier
                    .horizontalScroll(state = ScrollState(0), enabled = true)
                    .padding(vertical = 8.dp),
            ) {
                oddsList.forEachIndexed { index, value ->
                    RegularText(
                        text = "$index \n + $value",
                        modifier = Modifier.padding(horizontal = 4.dp),
                        textAlign = TextAlign.Center,
                    )
                }
            }
            Row(
                Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ButtonText(
                    modifier =
                        Modifier
                            .clickable {
                                viewModel.getRandomSelectedNumbers(selectedNumberCount)
                            },
                    text = stringResource(id = R.string.random_pick),
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = "",
                    tint = White,
                    modifier =
                        Modifier.clickable {
                            navController.navigate(NavItem.UpcomingGames.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        },
                )
                DropDownMenu(
                    items = GreekKinoNumberOfCombinations,
                    selectedNumber = selectedNumberCount,
                ) {
                    viewModel.updateNumberOfPossibleItems(it)
                }
            }
            LazyKinoBoard(itemsList = GreekKinoTalon, viewModel = viewModel)
        }
        Column(
            modifier =
                Modifier
                    .align(Alignment.BottomEnd)
                    .fillMaxWidth(),
        ) {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                RoundedCornerButton(
                    title = stringResource(id = R.string.select_kino),
                    isEnabled = selectedNumbers.size == selectedNumberCount,
                    onClick = {
                        showToast = true
                        toastMessageId =
                            if ((gameDetails?.getRemainingTime() ?: 0) > MIN_REMAINING_TIME) {
                                gameDetails?.drawId?.let {
                                    viewModel.playPickedNumbers(it)
                                }
                                R.string.successfully_saved
                            } else {
                                R.string.no_enough_remaining_time
                            }
                    },
                )
                if (showToast) {
                    ComposeToast(message = stringResource(id = toastMessageId))
                    showToast = false
                }

                RoundedTextView(
                    text =
                        stringResource(
                            id = R.string.selected_numbers,
                            selectedNumbers.size,
                            selectedNumberCount,
                        ),
                )
            }
            SubHeaderText(
                text =
                    stringResource(
                        id = R.string.time_remaining,
                        remainingTime,
                    ),
                modifier =
                    Modifier
                        .fillMaxWidth(),
            )
        }
    }
}
