package com.greek.kino.data.repository

import com.greek.kino.data.api.GreekKinoApi
import com.greek.kino.data.model.gameDetails.GameDetailsData
import com.greek.kino.domain.model.GameDetails
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(
    MockKExtension::class,
)
internal class GameDetailsRepositoryTest {
    private val greekKinoApi: GreekKinoApi = mockk<GreekKinoApi>(relaxed = true)

    private val gameDetailsRepositoryImpl =
        GameDetailsRepositoryImpl(greekKinoApi)

    @Test
    fun `when  reading UpcomingGames than 2 results should be shown`() =
        runTest {
            givenGameDetailsData()
            thanGameDetailsShouldBeVerified(20)
        }

    private suspend fun givenGameDetailsData() {
        coEvery { greekKinoApi.getGameDetailsData(20) } returns gameDetailsData
    }

    private suspend fun thanGameDetailsShouldBeVerified(drawId: Int) {
        gameDetailsRepositoryImpl.getGameDetails(drawId).shouldBe(gameDetails)
    }
}

private val gameDetailsData = GameDetailsData(1, 1, 1, 1, null, emptyList(), "", 1, null, null)
private val gameDetails =
    GameDetails(1, 1, 1, "1970-01-01", 1, null, emptyList(), "", 1, null, null)
