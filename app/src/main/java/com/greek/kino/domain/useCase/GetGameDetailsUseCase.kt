package com.greek.kino.domain.useCase

import com.greek.kino.domain.model.GameDetails
import com.greek.kino.domain.repository.GameDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGameDetailsUseCase
    @Inject
    constructor(
        private val gameDetailsRepository: GameDetailsRepository,
    ) {
        suspend operator fun invoke(drawId: Int): Flow<GameDetails> =
            flow {
                emit(gameDetailsRepository.getGameDetails(drawId))
            }
    }
