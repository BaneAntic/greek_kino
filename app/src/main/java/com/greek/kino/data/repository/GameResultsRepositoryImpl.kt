package com.greek.kino.data.repository

import com.greek.kino.data.api.GreekKinoApi
import com.greek.kino.data.model.gameResults.toListOfGameDetails
import com.greek.kino.domain.model.GameDetails
import com.greek.kino.domain.repository.GameResultsRepository
import javax.inject.Inject

class GameResultsRepositoryImpl
    @Inject
    constructor(
        private val api: GreekKinoApi,
    ) : GameResultsRepository {
        override suspend fun getPreviousGameResults(formattedDate: String): List<GameDetails> =
            api.getResultsForDate(formattedDate, formattedDate).toListOfGameDetails()
    }
