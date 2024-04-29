package com.greek.kino.data.model.common

import com.greek.kino.domain.model.Sidebets
import com.squareup.moshi.Json

data class SidebetsData(
    @Json(name = "columnNumbers")
    val columnNumbers: ColumnNumbersData?,
    @Json(name = "evenNumbers")
    val evenNumbers: List<Int?>?,
    @Json(name = "evenNumbersCount")
    val evenNumbersCount: Int?,
    @Json(name = "oddNumbers")
    val oddNumbers: List<Int?>?,
    @Json(name = "oddNumbersCount")
    val oddNumbersCount: Int?,
    @Json(name = "winningColumn")
    val winningColumn: Int?,
    @Json(name = "winningParity")
    val winningParity: String?,
)

fun SidebetsData.toSidebets() =
    Sidebets(
        columnNumbers?.toColumnNumbers(),
        evenNumbers,
        evenNumbersCount,
        oddNumbers,
        oddNumbersCount,
        winningColumn,
        winningParity,
    )
