package com.greek.kino.domain.useCase

import com.greek.kino.domain.repository.GameDatabaseRepository
import javax.inject.Inject

class UploadPlayedNumbersUseCase
    @Inject
    constructor(
        private val gameDatabaseRepository: GameDatabaseRepository,
    ) {
        suspend operator fun invoke(
            drawId: Int,
            playedNumbers: List<Int>,
        ) {
            gameDatabaseRepository.insertGame(drawId, playedNumbers)
        }
    }
