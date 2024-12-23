package com.example.cmong_pilates_attendance_project.utils

import android.content.Context
import android.widget.Toast


fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(resource: Int) {
    Toast.makeText(this, this.getString(resource), Toast.LENGTH_SHORT).show()
}