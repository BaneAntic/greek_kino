package com.greek.kino.data.repository

import com.greek.kino.data.api.GreekKinoApi
import com.greek.kino.data.model.upcomingGame.UpcomingGameDetailsData
import com.greek.kino.domain.model.UpcomingGameDetails
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
internal class UpcomingGamesRepositoryTest {
    private val greekKinoApi: GreekKinoApi = mockk<GreekKinoApi>(relaxed = true)

    private val upcomingGamesRepositoryImpl =
        UpcomingGamesRepositoryImpl(greekKinoApi)

    @Test
    fun `when  reading UpcomingGames for 0 games than empty list should be shown`() =
        runTest {
            givenEmptyListResult()
            thanEmptyListOFUpcomingGameDetailsShouldBeVerified(0, emptyList())
        }

    @Test
    fun `when  reading UpcomingGames than 2 results should be shown`() =
        runTest {
            givenListResultOf2Results()
            thanEmptyListOFUpcomingGameDetailsShouldBeVerified(20, listOfResults)
        }

    private suspend fun givenEmptyListResult() {
        coEvery { greekKinoApi.getUpcomingGames(0) } returns emptyList()
    }

    private suspend fun givenListResultOf2Results() {
        coEvery { greekKinoApi.getUpcomingGames(20) } returns listOfDataResults
    }

    private suspend fun thanEmptyListOFUpcomingGameDetailsShouldBeVerified(
        numberOfGames: Int,
        listOfGameDetails: List<UpcomingGameDetails>,
    ) {
        upcomingGamesRepositoryImpl.getUpcomingGames(numberOfGames).shouldBe(listOfGameDetails)
    }
}

private val listOfResults =
    listOf(
        UpcomingGameDetails(1, 1, 1, "01:00", 1, null, emptyList(), "", 1, null),
        UpcomingGameDetails(1, 1, 1, "01:00", 1, null, emptyList(), "", 1, null),
    )
private val listOfDataResults =
    listOf(
        UpcomingGameDetailsData(1, 1, 1, 1, null, emptyList(), "", 1, null),
        UpcomingGameDetailsData(1, 1, 1, 1, null, emptyList(), "", 1, null),
    )
