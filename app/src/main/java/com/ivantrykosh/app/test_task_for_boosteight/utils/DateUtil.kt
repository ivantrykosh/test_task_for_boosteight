package com.ivantrykosh.app.test_task_for_boosteight.utils

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

/**
 * Contains date and time as strings
 */
data class StringDateAndTime(
    val stringDate: String,
    val stringTime: String
)

/**
 * Contains useful methods with Date
 */
object DateUtil {
    /**
     * Get current time in seconds for UTC zone
     */
    fun getCurrentTimeInSeconds() =
        LocalDateTime.now()
            .atZone(ZoneId.systemDefault())
            .withZoneSameInstant(ZoneOffset.UTC).toEpochSecond()

    /**
     * Get date and time as strings by converting passed seconds to datetime format with local time zone
     */
    fun getStringDateAndTime(timeInSeconds: Long) =
        LocalDateTime.ofEpochSecond(timeInSeconds, 0, ZoneOffset.UTC)
            .atZone(ZoneOffset.UTC)
            .withZoneSameInstant(ZoneId.systemDefault())
            .toLocalDateTime()
            .format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"))
            .let {
                StringDateAndTime(
                    stringDate = it.split(" ")[1],
                    stringTime = it.split(" ")[0],
                )
            }
}