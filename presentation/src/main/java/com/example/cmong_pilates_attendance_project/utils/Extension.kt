package com.example.cmong_pilates_attendance_project.utils

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale


fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun String.isValidPhoneNumber(): Boolean {
    val regex = Regex("^010[0-9]{8}\$")
    if (regex.matches(this)) {
        return true
    }
    return false
}

fun String.toTimestamp(): Long {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val localDate = LocalDate.parse(this, formatter)
    return localDate.atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000
}

fun Long.toDateString()
        : String {
    val date = Date(this)
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(date)
}

fun String.toDateTriple(): Triple<Int, Int, Int>? {
    val parts = this.split("-")
    return if (parts.size == 3) {
        Triple(parts[0].toInt(), parts[1].toInt() - 1, parts[2].toInt())
    } else {
        null
    }
}