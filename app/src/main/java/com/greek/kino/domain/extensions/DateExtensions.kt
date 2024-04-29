package com.greek.kino.domain.extensions

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Long.timeStampToDate(): String {
    val date =
        Instant
            .ofEpochMilli(this)
            .atZone(ZoneId.systemDefault()) // change time zone if necessary
            .toLocalDateTime()

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    return formatter.format(date)
}

fun Long.timeStampToHoursAndMinutes(): String {
    val date =
        Instant
            .ofEpochMilli(this)
            .atZone(ZoneId.systemDefault()) // change time zone if necessary
            .toLocalDateTime()

    val formatter = DateTimeFormatter.ofPattern("HH:mm")

    return formatter.format(date)
}

fun Long.millisecondsToHoursAndMinutes(): String {
    val seconds = (this / 1000) % 60
    val minutes = this / (1000 * 60) % 60
    val hours = this / (1000 * 60 * 60) % 24

    return if (hours > 0) {
        String.format("%02d:%02d:%02d", hours, minutes, seconds)
    } else {
        return String.format("%02d:%02d", minutes, seconds)
    }
}

const val MIN_REMAINING_TIME = 15000
const val TIMER_REFRESH_TIME: Long = 1000
const val DEFAULT_PASSED_TIME_FORMAT = "00:00"
