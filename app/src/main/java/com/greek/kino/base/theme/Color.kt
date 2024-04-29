package com.greek.kino.base.theme

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val White = Color(0xFFFFFFFF)
val Background = Color(0xFF000000)
val PrimaryBlue = Color(0xFF0D1C42)
val PrimaryOrange = Color(0xFFFEB912)
val SecondaryBlue = Color(0xFF40A3FF)
val HeaderGray = Color(0xFF272727)
val TextDarkGray = Color(0xFF414141)

fun randomColor(
    isSelected: Boolean,
    alpha: Int = 255,
) = if (isSelected) {
    Color(
        Random.nextInt(256),
        Random.nextInt(256),
        Random.nextInt(256),
        alpha = alpha,
    )
} else {
    Color.Transparent
}
