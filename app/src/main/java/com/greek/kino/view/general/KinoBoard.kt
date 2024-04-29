package com.greek.kino.view.general

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.greek.kino.base.components.CustomGridView

@Composable
fun KinoBoard(
    itemsList: List<Int>,
    isClickable: Boolean,
) {
    CustomGridView(
        columns = 8,
        itemCount = itemsList.size,
        modifier =
            Modifier
                .fillMaxSize()
                .padding(8.dp),
    ) {
        KinoItem(number = it, isClickable = isClickable, true) {
            false
        }
    }
}
