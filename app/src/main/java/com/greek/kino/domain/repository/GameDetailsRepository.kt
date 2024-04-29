package com.greek.kino.domain.repository

import com.greek.kino.domain.model.GameDetails

interface GameDetailsRepository {
    suspend fun getGameDetails(drawId: Int): GameDetails
}
