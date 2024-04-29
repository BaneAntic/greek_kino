package com.greek.kino.domain.model

import com.greek.kino.domain.extensions.DEFAULT_PASSED_TIME_FORMAT
import com.greek.kino.domain.extensions.millisecondsToHoursAndMinutes
import java.util.Calendar

data class GameDetails(
    val drawBreak: Int = 0,
    val drawId: Int = 0,
    val drawTime: Long = 0,
    val drawTimeFormatted: String = "",
    val gameId: Int = 0,
    val pricePoints: PricePoints? = null,
    val prizeCategories: List<PrizeCategory?>? = emptyList(),
    val status: String = "",
    val visualDraw: Int = 0,
    val wagerStatistics: WagerStatistics? = null,
    val winningNumbers: WinningNumbers? = null,
) {
    fun getRemainingTimeAsString(): String {
        val remainingTime = drawTime - Calendar.getInstance().timeInMillis
        return when (remainingTime <= 0) {
            true -> DEFAULT_PASSED_TIME_FORMAT
            else -> remainingTime.millisecondsToHoursAndMinutes()
        }
    }

    fun getRemainingTime() = drawTime - Calendar.getInstance().timeInMillis
}
