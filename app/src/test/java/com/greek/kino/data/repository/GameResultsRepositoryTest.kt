package com.greek.kino.data.repository

import com.greek.kino.data.api.GreekKinoApi
import com.greek.kino.data.model.gameDetails.GameDetailsData
import com.greek.kino.data.model.gameResults.GameResultsData
import com.greek.kino.domain.model.GameDetails
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class GameResultsRepositoryTest {
    private val greekKinoApi: GreekKinoApi = mockk<GreekKinoApi>(relaxed = true)

    private val gameResultsRepositoryImpl =
        GameResultsRepositoryImpl(greekKinoApi)

    @Test
    fun `when reading GameResultsData than 2 results should be shown`() =
        runTest {
            givenGameResultsData()
            thanGameResultsShouldBeVerified()
        }

    private suspend fun givenGameResultsData() {
        coEvery {
            greekKinoApi.getResultsForDate(
                "1970-01-01",
                "1970-01-01",
            )
        } returns gameResultsData
    }

    private suspend fun thanGameResultsShouldBeVerified() {
        gameResultsRepositoryImpl.getPreviousGameResults("1970-01-01").shouldBe(gameResults)
    }
}

private val gameResultsData =
    GameResultsData(listOf(GameDetailsData(1, 1, 1, 1, null, emptyList(), "", 1, null, null)))
private val gameResults =
    listOf(GameDetails(1, 1, 1, "1970-01-01", 1, null, emptyList(), "", 1, null, null))
