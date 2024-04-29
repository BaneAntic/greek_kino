package com.greek.kino.data.repository

import com.greek.kino.data.database.dao.GameDao
import com.greek.kino.data.database.data.GameDbDetails
import com.greek.kino.domain.repository.GameDatabaseRepository
import javax.inject.Inject

class GameDatabaseRepositoryImpl
    @Inject
    constructor(private val gameDao: GameDao) :
    GameDatabaseRepository {
        override suspend fun insertGame(
            drawId: Int,
            playedNumbers: List<Int>,
        ) {
            val gameDbDetails = GameDbDetails(drawId = drawId, playedNumbers = playedNumbers.joinToString())
            gameDao.insertNewGame(gameDbDetails)
        }

        override suspend fun getAllGames() {
            gameDao.getAllPlayedGames()
        }
    }
