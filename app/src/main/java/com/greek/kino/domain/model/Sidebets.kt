package com.greek.kino.domain.model

data class Sidebets(
    val columnNumbers: ColumnNumbers?,
    val evenNumbers: List<Int?>?,
    val evenNumbersCount: Int?,
    val oddNumbers: List<Int?>?,
    val oddNumbersCount: Int?,
    val winningColumn: Int?,
    val winningParity: String?,
)
