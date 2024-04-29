package com.greek.kino.domain.di

import com.greek.kino.domain.repository.GameDatabaseRepository
import com.greek.kino.domain.repository.GameDetailsRepository
import com.greek.kino.domain.repository.GameResultsRepository
import com.greek.kino.domain.repository.UpcomingGamesRepository
import com.greek.kino.domain.useCase.GetGameDetailsUseCase
import com.greek.kino.domain.useCase.GetGameResultsUseCase
import com.greek.kino.domain.useCase.GetRandomSelectedNumbersUseCase
import com.greek.kino.domain.useCase.GetUpcomingGamesUseCase
import com.greek.kino.domain.useCase.UploadPlayedNumbersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun providesGetGameDetailsUseCaseDetailsUseCase(gameDetailsRepository: GameDetailsRepository) =
        GetGameDetailsUseCase(gameDetailsRepository)

    @Provides
    @Singleton
    fun providesGetGameResultsUseCase(gameResultsRepository: GameResultsRepository) = GetGameResultsUseCase(gameResultsRepository)

    @Provides
    @Singleton
    fun providesGetUpcomingGamesUseCase(upcomingGamesRepository: UpcomingGamesRepository) = GetUpcomingGamesUseCase(upcomingGamesRepository)

    @Provides
    @Singleton
    fun providesGetRandomSelectedNumbersUseCase() = GetRandomSelectedNumbersUseCase()

    @Provides
    @Singleton
    fun providesUploadPlayedNumbersUseCase(gameDatabaseRepository: GameDatabaseRepository) =
        UploadPlayedNumbersUseCase(gameDatabaseRepository)
}
