package com.greek.kino.domain.usecase

import com.greek.kino.domain.model.GameDetails
import com.greek.kino.domain.repository.GameDetailsRepository
import com.greek.kino.domain.useCase.GetGameDetailsUseCase
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class
GetGameDomainDetailsTest {
    private val gameDetailsRepository: GameDetailsRepository = mockk()
    private val getGameDetailsUseCase = GetGameDetailsUseCase(gameDetailsRepository)

    @Test
    fun `when gameDetails are set while running use case than flow with gameDetails will be return`() =
        runTest {
            givenGameDetailsResultWithOriginalValue()
            thanGameDetailsUseCaseShouldContainMappedFlowOFGameDetails(gameDetails)
        }

    private fun givenGameDetailsResultWithOriginalValue() {
        coEvery { gameDetailsRepository.getGameDetails(0) } returns gameDetails
    }

    private suspend fun thanGameDetailsUseCaseShouldContainMappedFlowOFGameDetails(gameDetails: GameDetails) {
        getGameDetailsUseCase(0).first().shouldBe(gameDetails)
    }
}

private val gameDetails =
    GameDetails(1, 1, 1, "1970-01-01", 1, null, emptyList(), "", 1, null, null)
