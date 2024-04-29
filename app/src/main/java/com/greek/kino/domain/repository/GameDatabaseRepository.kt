package com.greek.kino.domain.repository

interface GameDatabaseRepository {
    suspend fun insertGame(
        drawId: Int,
        playedNumbers: List<Int>,
    )

    suspend fun getAllGames()
}
