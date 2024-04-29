package com.greek.kino.data.di

import com.greek.kino.data.repository.GameDatabaseRepositoryImpl
import com.greek.kino.data.repository.GameDetailsRepositoryImpl
import com.greek.kino.data.repository.GameResultsRepositoryImpl
import com.greek.kino.data.repository.UpcomingGamesRepositoryImpl
import com.greek.kino.domain.repository.GameDatabaseRepository
import com.greek.kino.domain.repository.GameDetailsRepository
import com.greek.kino.domain.repository.GameResultsRepository
import com.greek.kino.domain.repository.UpcomingGamesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindsGameDetailsRepository(gameDetailsRepository: GameDetailsRepositoryImpl): GameDetailsRepository

    @Singleton
    @Binds
    abstract fun bindsGameResultsRepository(gameDetailsRepository: GameResultsRepositoryImpl): GameResultsRepository

    @Singleton
    @Binds
    abstract fun bindsUpcomingGamesRepository(gameDetailsRepository: UpcomingGamesRepositoryImpl): UpcomingGamesRepository

    @Singleton
    @Binds
    abstract fun bindsGameDatabaseRepository(gameDatabaseRepository: GameDatabaseRepositoryImpl): GameDatabaseRepository
}
