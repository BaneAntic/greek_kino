package com.greek.kino.data.model.gameDetails

import com.greek.kino.data.model.common.PricePointsData
import com.greek.kino.data.model.common.PrizeCategoryData
import com.greek.kino.data.model.common.WagerStatisticsData
import com.greek.kino.data.model.common.WinningNumbersData
import com.greek.kino.data.model.common.toPricePoints
import com.greek.kino.data.model.common.toPrizeCategory
import com.greek.kino.data.model.common.toWagerStatistics
import com.greek.kino.data.model.common.toWinningNumbers
import com.greek.kino.domain.extensions.timeStampToDate
import com.greek.kino.domain.model.GameDetails
import com.squareup.moshi.Json

data class GameDetailsData(
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
    @Json(name = "winningNumbers")
    val winningNumbers: WinningNumbersData?,
)

fun GameDetailsData.toGameDetails() =
    GameDetails(
        drawBreak ?: 0,
        drawId ?: 0,
        drawTime ?: 0,
        drawTimeFormatted = drawTime?.timeStampToDate() ?: "",
        gameId ?: 0,
        pricePoints?.toPricePoints(),
        prizeCategories?.map { it?.toPrizeCategory() },
        status ?: "",
        visualDraw ?: 0,
        wagerStatistics?.toWagerStatistics(),
        winningNumbers?.toWinningNumbers(),
    )
