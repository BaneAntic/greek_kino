package com.greek.kino.domain.useCase

import com.greek.kino.domain.model.UpcomingGameDetails
import com.greek.kino.domain.repository.UpcomingGamesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUpcomingGamesUseCase
    @Inject
    constructor(
        private val upcomingGamesRepository: UpcomingGamesRepository,
    ) {
        suspend operator fun invoke(): Flow<List<UpcomingGameDetails>> =
            flow {
                emit(upcomingGamesRepository.getUpcomingGames(NUMBER_OF_GAMES))
            }
    }

private const val NUMBER_OF_GAMES = 20
