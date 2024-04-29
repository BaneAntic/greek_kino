package com.greek.kino.data.database.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class GameDbDetails(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "drawId") val drawId: Int,
    @ColumnInfo(name = "numbers") val playedNumbers: String,
)
