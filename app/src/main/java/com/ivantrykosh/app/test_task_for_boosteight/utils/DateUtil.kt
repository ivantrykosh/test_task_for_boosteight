package com.ivantrykosh.app.test_task_for_boosteight.utils

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

data class StringDateAndTime(
    val stringDate: String,
    val stringTime: String
)

object DateUtil {
    fun getCurrentTimeInSeconds() =
        LocalDateTime.now()
            .atZone(ZoneId.systemDefault())
            .withZoneSameInstant(ZoneOffset.UTC).toEpochSecond()

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