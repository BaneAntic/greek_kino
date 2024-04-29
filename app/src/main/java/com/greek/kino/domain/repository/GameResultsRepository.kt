package com.greek.kino.domain.repository

import com.greek.kino.domain.model.GameDetails

interface GameResultsRepository {
    suspend fun getPreviousGameResults(formattedDate: String): List<GameDetails>
}
