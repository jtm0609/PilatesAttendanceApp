package com.example.core_android.utils

object DateFormatter {
    fun formatDate(year: Int, month: Int, day: Int): String {
        return String.format("%d-%02d-%02d", year, month, day)
    }
}