package com.greek.kino.data.api

import com.greek.kino.data.model.gameDetails.GameDetailsData
import com.greek.kino.data.model.gameResults.GameResultsData
import com.greek.kino.data.model.upcomingGame.UpcomingGameDetailsData
import retrofit2.http.GET
import retrofit2.http.Path

interface GreekKinoApi {
    @GET("$GAME_ID/{drawId}")
    suspend fun getGameDetailsData(
        @Path("drawId") userId: Int,
    ): GameDetailsData

    @GET("$GAME_ID/upcoming/{numberOfUpcomingGames}")
    suspend fun getUpcomingGames(
        @Path("numberOfUpcomingGames") numberOfUpcomingGames: Int,
    ): List<UpcomingGameDetailsData>

    @GET("$GAME_ID/draw-date/{startDate}/{endDate}")
    suspend fun getResultsForDate(
        @Path("startDate") startDate: String,
        @Path("endDate") endDate: String,
    ): GameResultsData
}

private const val GAME_ID = 1100
