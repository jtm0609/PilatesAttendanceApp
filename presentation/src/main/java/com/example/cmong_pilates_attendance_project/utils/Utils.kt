package com.example.cmong_pilates_attendance_project.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale

object Utils {

    //타임스탬프 -> 문자열(yyyy-MM-dd)
    fun convertTimeStampToDateString(timeStamp: Long)
            : String {
        val date = Date(timeStamp)
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(date)
    }

    //문자열(yyyy-MM-dd) -> 타임스탬프
    @RequiresApi(Build.VERSION_CODES.O)
    fun dateStringToTimestamp(dateString: String): Long {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val localDate = LocalDate.parse(dateString, formatter)
        return localDate.atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000
    }

    //끝나는 날짜 변환() = 시작날짜 + 기간
    @RequiresApi(Build.VERSION_CODES.O)
    fun getEndDateTimeMilli(startDate: Long, duration: String): Long {
        val now = Instant.now()
        val instant = Instant.ofEpochMilli(startDate)
        var endDate: Instant? = null
        when (duration) {
            "2주" -> {
                endDate = instant.plus(15, ChronoUnit.DAYS)
            }

            "4주" -> {
                endDate = instant.plus(29, ChronoUnit.DAYS)
            }

            "8주" -> {
                endDate = instant.plus(57, ChronoUnit.DAYS)
            }

            "12주" -> {
                endDate = instant.plus(85, ChronoUnit.DAYS)
            }
        }

        return endDate?.toEpochMilli()!!
    }

    fun isValidPhoneNumber(cellphoneNumber: String): Boolean {
        var returnValue = false;
        var regex = Regex("^010[0-9]{8}\$")
        if (regex.matches(cellphoneNumber)) {
            return true
        }

        return returnValue
    }
}