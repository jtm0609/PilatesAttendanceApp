package com.example.domain.model

import java.time.Instant
import java.time.temporal.ChronoUnit

enum class Duration(val weeks: Int, private val lengthInDays: Long) {
    TWO_WEEKS(2, 15),
    FOUR_WEEKS(4, 29),
    EIGHT_WEEKS(8, 57),
    TWELVE_WEEKS(12, 85);

    val label: String get() = "${weeks}주"

    fun endDateTime(startDateTime: Long): Long =
        Instant.ofEpochMilli(startDateTime).plus(lengthInDays, ChronoUnit.DAYS).toEpochMilli()

    companion object {
        fun fromLabel(label: String): Duration? = entries.firstOrNull { it.label == label }
    }
}
