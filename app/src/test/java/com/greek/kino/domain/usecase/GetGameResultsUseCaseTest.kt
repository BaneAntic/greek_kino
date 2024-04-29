package com.greek.kino.domain.usecase

import com.greek.kino.domain.model.GameDetails
import com.greek.kino.domain.repository.GameResultsRepository
import com.greek.kino.domain.useCase.GetGameResultsUseCase
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetGameResultsUseCaseTest {
    private val gameResultsRepository: GameResultsRepository = mockk()
    private val getGameResultsUseCase = GetGameResultsUseCase(gameResultsRepository)

    @Test
    fun `when gameDetails are set while running use case than flow with gameDetails will be return`() =
        runTest {
            givenGameDetailsResultWithOriginalValue()
            thanGameDetailsUseCaseShouldContainMappedFlowOFGameDetails(gameResults)
        }

    private fun givenGameDetailsResultWithOriginalValue() {
        coEvery { gameResultsRepository.getPreviousGameResults("1970-01-01") } returns gameResults
    }

    private suspend fun thanGameDetailsUseCaseShouldContainMappedFlowOFGameDetails(gameDetails: List<GameDetails>) {
        getGameResultsUseCase("1970-01-01").first().shouldBe(gameDetails)
    }
}

private val gameResults =
    listOf(GameDetails(1, 1, 1, "1970-01-01", 1, null, emptyList(), "", 1, null, null))
