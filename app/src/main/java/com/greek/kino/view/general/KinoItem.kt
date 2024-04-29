package com.greek.kino.view.general

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.greek.kino.base.components.KinoText
import com.greek.kino.base.theme.TextDarkGray
import com.greek.kino.base.theme.randomColor

@Composable
fun KinoItem(
    number: Int,
    isClickable: Boolean = true,
    isItemSelected: Boolean = true,
    onSelected: () -> Boolean,
) {
    var isSelected by remember { mutableStateOf(isItemSelected) }

    Box(
        modifier =
            Modifier
                .border(0.2.dp, TextDarkGray)
                .aspectRatio(1f, true),
    ) {
        Box(
            modifier =
                Modifier
                    .size(100.dp)
                    .border(4.dp, randomColor(isSelected), CircleShape)
                    .background(Color.Black)
                    .clickable {
                        if (isClickable) {
                            if (onSelected()) {
                                isSelected = !isSelected
                            }
                        }
                    },
            contentAlignment = Alignment.Center,
        ) {
            KinoText(
                text = "$number",
                isSelected = isSelected,
            )
        }
    }
}
