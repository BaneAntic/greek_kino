package com.greek.kino.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.greek.kino.data.database.data.GameDbDetails

@Dao
interface GameDao {
    @Query("SELECT * FROM games")
    fun getAllPlayedGames(): List<GameDbDetails>

    @Insert
    fun insertNewGame(jsonGame: GameDbDetails)
}
