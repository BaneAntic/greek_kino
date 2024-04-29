package com.greek.kino.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.greek.kino.data.database.dao.GameDao
import com.greek.kino.data.database.data.GameDbDetails

@Database(entities = [GameDbDetails::class], version = 1)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}
