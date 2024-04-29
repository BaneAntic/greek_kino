package com.greek.kino.data.model.common

import com.greek.kino.domain.model.WagerStatistics
import com.squareup.moshi.Json

data class WagerStatisticsData(
    @Json(name = "addOn")
    val addOn: List<AddOnData?>?,
    @Json(name = "columns")
    val columns: Int?,
    @Json(name = "wagers")
    val wagers: Int?,
)

fun WagerStatisticsData.toWagerStatistics() = WagerStatistics(addOn?.map { it?.toAddOn() }, columns, wagers)
