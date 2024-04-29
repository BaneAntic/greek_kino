package com.greek.kino.domain.model

data class WinningNumbers(
    val bonus: List<Int>?,
    val list: List<Int> = emptyList(),
    val sidebets: Sidebets?,
)
