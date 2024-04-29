package com.greek.kino.domain.repository

import com.greek.kino.domain.model.UpcomingGameDetails

interface UpcomingGamesRepository {
    suspend fun getUpcomingGames(numberOfGames: Int): List<UpcomingGameDetails>
}
