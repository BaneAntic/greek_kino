package com.greek.kino.data.model.gameResults

import com.greek.kino.data.model.gameDetails.GameDetailsData
import com.greek.kino.data.model.gameDetails.toGameDetails
import com.squareup.moshi.Json

data class GameResultsData(
    @Json(name = "content")
    val content: List<GameDetailsData>?,
)

fun GameResultsData.toListOfGameDetails() = content?.map { it.toGameDetails() } ?: emptyList()
