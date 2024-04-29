package com.greek.kino.base.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.greek.kino.R

@Composable
fun DropDownMenu(
    items: List<Int>,
    selectedNumber: Int,
    onNumberChanged: (number: Int) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier =
            Modifier
                .wrapContentSize(Alignment.TopStart),
    ) {
        Text(
            stringResource(id = R.string.dropdown_numbers, selectedNumber),
            color = Color.White,
            modifier =
                Modifier
                    .clickable(onClick = { expanded = true })
                    .background(
                        Color.Transparent,
                    ),
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier =
                Modifier
                    .height(250.dp)
                    .background(
                        Color.Black,
                    ),
        ) {
            items.forEach {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "$it",
                            color = Color.White,
                        )
                    },
                    onClick = {
                        onNumberChanged(it)
                        expanded = false
                    },
                )
            }
        }
    }
}
