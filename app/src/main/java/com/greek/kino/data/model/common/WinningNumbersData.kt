package com.greek.kino.data.model.common

import com.greek.kino.domain.model.WinningNumbers
import com.squareup.moshi.Json

data class WinningNumbersData(
    @Json(name = "bonus")
    val bonus: List<Int>?,
    @Json(name = "list")
    val list: List<Int>?,
    @Json(name = "sidebets")
    val sidebets: SidebetsData?,
)

fun WinningNumbersData.toWinningNumbers() = WinningNumbers(bonus, list ?: emptyList(), sidebets?.toSidebets())
