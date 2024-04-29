package com.greek.kino.domain.useCase

import com.greek.kino.domain.model.GameDetails
import com.greek.kino.domain.repository.GameResultsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGameResultsUseCase
    @Inject
    constructor(
        private val gameResultsRepository: GameResultsRepository,
    ) {
        suspend operator fun invoke(formattedDate: String): Flow<List<GameDetails>> =
            flow {
                emit(gameResultsRepository.getPreviousGameResults(formattedDate))
            }
    }
