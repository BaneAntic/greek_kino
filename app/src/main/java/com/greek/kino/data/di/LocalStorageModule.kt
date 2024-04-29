package com.greek.kino.data.di

import android.content.Context
import androidx.room.Room
import com.greek.kino.data.database.GameDatabase
import com.greek.kino.data.database.dao.GameDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalStorageModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): GameDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            GameDatabase::class.java,
            "games",
        ).build()

    @Singleton
    @Provides
    fun provideGameDao(database: GameDatabase): GameDao = database.gameDao()
}
