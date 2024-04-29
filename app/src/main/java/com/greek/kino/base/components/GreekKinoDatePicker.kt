package com.greek.kino.base.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.greek.kino.R
import com.greek.kino.base.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreekKinoDatePicker(onDateSelected: (milliseconds: Long) -> Unit) {
    var showDialog by rememberSaveable { mutableStateOf(false) }
    val dateState = rememberDatePickerState()

    Column {
        Icon(
            painter = painterResource(id = R.drawable.ic_calendar),
            contentDescription = "",
            tint = White,
            modifier =
                Modifier
                    .padding(all = 8.dp)
                    .clickable {
                        showDialog = true
                    },
        )
        if (showDialog) {
            DatePickerDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                            onDateSelected(
                                dateState.selectedDateMillis ?: 0,
                            )
                        },
                    ) {
                        Text(text = "OK")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            showDialog = false
                        },
                    ) {
                        RegularText(text = "Cancel")
                    }
                },
            ) {
                DatePicker(
                    state = dateState,
                )
            }
        }
    }
}
