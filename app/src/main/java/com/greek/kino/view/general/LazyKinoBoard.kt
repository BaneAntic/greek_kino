package com.greek.kino.view.general

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.greek.kino.viewModel.ResultsViewModel

@Composable
fun LazyKinoBoard(
    itemsList: List<Int>,
    viewModel: ResultsViewModel,
) {
    val selectedNumbers by viewModel.selectedNumbers.collectAsState(emptyList())

    LazyVerticalGrid(
        columns = GridCells.Fixed(10),
        state = rememberLazyGridState(),
    ) {
        items(itemsList) { item ->
            if (selectedNumbers.contains(item)) {
                KinoItem(
                    number = item,
                    isClickable = true,
                    isItemSelected = true,
                ) {
                    viewModel.removeSelectedNumberFromList(item)
                }
            } else {
                KinoItem(
                    number = item,
                    isClickable = true,
                    isItemSelected = false,
                ) {
                    viewModel.addSelectedNumberInList(item)
                }
            }
        }
    }
}
