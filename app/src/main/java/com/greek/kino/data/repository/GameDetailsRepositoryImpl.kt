package com.greek.kino.data.repository

import com.greek.kino.data.api.GreekKinoApi
import com.greek.kino.data.model.gameDetails.toGameDetails
import com.greek.kino.domain.repository.GameDetailsRepository
import javax.inject.Inject

class GameDetailsRepositoryImpl @Inject constructor(
    private val api: GreekKinoApi
): GameDetailsRepository {
    override suspend fun getGameDetails(drawId: Int) =
       api.getGameDetailsData(drawId).toGameDetails()
}