package com.greek.kino.view.results

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.greek.kino.R
import com.greek.kino.base.components.GreekKinoDatePicker
import com.greek.kino.base.components.HeaderText
import com.greek.kino.base.components.SubHeaderText
import com.greek.kino.base.theme.HeaderGray
import com.greek.kino.domain.extensions.timeStampToDate
import com.greek.kino.view.general.KinoBoard
import com.greek.kino.viewModel.ResultsViewModel
import java.util.Calendar

@Composable
fun ResultsScreen(viewModel: ResultsViewModel) {
    val gameResults by viewModel.gameResults.collectAsState(initial = emptyList())
    var workCompleted by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (!workCompleted) {
            viewModel.loadResultsFor(Calendar.getInstance().timeInMillis.timeStampToDate())
            workCompleted = true
        }
    }

    LazyColumn {
        item {
            GreekKinoDatePicker {
                viewModel.loadResultsFor(it.timeStampToDate())
            }
        }
        if (gameResults.isNotEmpty()) {
            items(items = gameResults, itemContent = { item ->
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .background(HeaderGray)
                            .padding(8.dp)
                            .clickable {},
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    SubHeaderText(
                        text =
                            stringResource(
                                id = R.string.draw_time_leg,
                                item.drawTimeFormatted,
                                item.drawId.toString(),
                            ),
                    )
                }
                KinoBoard(itemsList = item.winningNumbers?.list ?: emptyList(), false)
            })
        } else {
            item {
                HeaderText(
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.no_data_for_selected_date),
                )
            }
        }
    }
}
