package com.greek.kino.data.model.common

import com.greek.kino.domain.model.ColumnNumbers
import com.squareup.moshi.Json

data class ColumnNumbersData(
    @Json(name = "1")
    val x1: List<Int?>?,
    @Json(name = "10")
    val x10: List<Int?>?,
    @Json(name = "2")
    val x2: List<Int?>?,
    @Json(name = "3")
    val x3: List<Int?>?,
    @Json(name = "4")
    val x4: List<Int?>?,
    @Json(name = "5")
    val x5: List<Int?>?,
    @Json(name = "6")
    val x6: List<Int?>?,
    @Json(name = "7")
    val x7: List<Int?>?,
    @Json(name = "8")
    val x8: List<Int?>?,
    @Json(name = "9")
    val x9: List<Int?>?,
)

fun ColumnNumbersData.toColumnNumbers() = ColumnNumbers(x1, x10, x2, x3, x4, x5, x6, x7, x8, x9)
