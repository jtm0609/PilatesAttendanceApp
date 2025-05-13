package com.example.domain.model

import com.example.domain.utils.toDateString
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id : Long,
    var name: String,
    var phoneNumber: String,
    var duration: String,
    var startDateTime: Long,
    var endDateTime: Long,
    var mileage: Int,
    var attendanceCountOfToday: Int,
    var attendanceDate: Long
) {

    fun getStartDate(): Date {
        val dateString = startDateTime.toDateString()
        val (year, month, day) = dateString.split("-").map{it.toInt()}
        return Date(year, month - 1, day)
    }

    fun getStartDateText(): String {
        return startDateTime.toDateString()
    }
}
