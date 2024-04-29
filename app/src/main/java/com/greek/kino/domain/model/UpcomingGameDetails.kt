package com.greek.kino.domain.model

import com.greek.kino.domain.extensions.DEFAULT_PASSED_TIME_FORMAT
import com.greek.kino.domain.extensions.millisecondsToHoursAndMinutes
import java.util.Calendar

data class UpcomingGameDetails(
    val drawBreak: Int?,
    val drawId: Int?,
    val drawTime: Long = 0,
    val drawTimeFormatted: String = "",
    val gameId: Int?,
    val pricePoints: PricePoints?,
    val prizeCategories: List<PrizeCategory?>?,
    val status: String?,
    val visualDraw: Int?,
    val wagerStatistics: WagerStatistics?,
) {
    fun getRemainingTime(): String {
        val remainingTime = drawTime - Calendar.getInstance().timeInMillis
        return when (remainingTime <= 0) {
            true -> DEFAULT_PASSED_TIME_FORMAT
            else -> remainingTime.millisecondsToHoursAndMinutes()
        }
    }
}
