package com.greek.kino.data.model.upcomingGame

import com.greek.kino.data.model.common.PricePointsData
import com.greek.kino.data.model.common.PrizeCategoryData
import com.greek.kino.data.model.common.WagerStatisticsData
import com.greek.kino.data.model.common.toPricePoints
import com.greek.kino.data.model.common.toPrizeCategory
import com.greek.kino.data.model.common.toWagerStatistics
import com.greek.kino.domain.extensions.timeStampToHoursAndMinutes
import com.greek.kino.domain.model.UpcomingGameDetails
import com.squareup.moshi.Json

data class UpcomingGameDetailsData(
    @Json(name = "drawBreak")
    val drawBreak: Int?,
    @Json(name = "drawId")
    val drawId: Int?,
    @Json(name = "drawTime")
    val drawTime: Long?,
    @Json(name = "gameId")
    val gameId: Int?,
    @Json(name = "pricePoints")
    val pricePoints: PricePointsData?,
    @Json(name = "prizeCategories")
    val prizeCategories: List<PrizeCategoryData?>?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "visualDraw")
    val visualDraw: Int?,
    @Json(name = "wagerStatistics")
    val wagerStatistics: WagerStatisticsData?,
)

fun UpcomingGameDetailsData.toUpcomingGameDetails() =
    UpcomingGameDetails(
        drawBreak,
        drawId,
        drawTime ?: 0,
        drawTime?.timeStampToHoursAndMinutes() ?: "",
        gameId,
        pricePoints?.toPricePoints(),
        prizeCategories?.map { it?.toPrizeCategory() },
        status,
        visualDraw,
        wagerStatistics?.toWagerStatistics(),
    )
