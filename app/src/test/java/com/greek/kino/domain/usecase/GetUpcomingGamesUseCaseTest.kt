package com.greek.kino.domain.usecase

import com.greek.kino.domain.model.UpcomingGameDetails
import com.greek.kino.domain.repository.UpcomingGamesRepository
import com.greek.kino.domain.useCase.GetUpcomingGamesUseCase
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetUpcomingGamesUseCaseTest {
    private val upcomingGamesRepository: UpcomingGamesRepository = mockk()
    private val getUpcomingGamesUseCase = GetUpcomingGamesUseCase(upcomingGamesRepository)

    @Test
    fun `when gameDetails are set while running use case than flow with gameDetails will be return`() =
        runTest {
            givenGameDetailsResultWithOriginalValue()
            thanGameDetailsUseCaseShouldContainMappedFlowOFGameDetails(listOfResults)
        }

    private fun givenGameDetailsResultWithOriginalValue() {
        coEvery { upcomingGamesRepository.getUpcomingGames(20) } returns listOfResults
    }

    private suspend fun thanGameDetailsUseCaseShouldContainMappedFlowOFGameDetails(gameDetails: List<UpcomingGameDetails>) {
        getUpcomingGamesUseCase().first().shouldBe(gameDetails)
    }
}

private val listOfResults =
    listOf(
        UpcomingGameDetails(1, 1, 1, "01:00", 1, null, emptyList(), "", 1, null),
        UpcomingGameDetails(1, 1, 1, "01:00", 1, null, emptyList(), "", 1, null),
    )
