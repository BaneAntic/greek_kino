package com.greek.kino.data.repository

import com.greek.kino.data.api.GreekKinoApi
import com.greek.kino.data.model.upcomingGame.toUpcomingGameDetails
import com.greek.kino.domain.model.UpcomingGameDetails
import com.greek.kino.domain.repository.UpcomingGamesRepository
import javax.inject.Inject

class UpcomingGamesRepositoryImpl @Inject constructor(
    private val api: GreekKinoApi
) : UpcomingGamesRepository {
    override suspend fun getUpcomingGames(numberOfGames: Int): List<UpcomingGameDetails> =
        api.getUpcomingGames(NUMBER_OF_UPCOMING_GAMES).map {
            it.toUpcomingGameDetails()
        }
}

private const val NUMBER_OF_UPCOMING_GAMES = 20
