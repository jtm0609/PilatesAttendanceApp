package com.example.cmong_pilates_attendance_project.ui.attendance.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.base.BaseActivity
import com.example.cmong_pilates_attendance_project.databinding.ActivityAttendanceBinding
import com.example.cmong_pilates_attendance_project.ui.PilatesApp
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AttendanceActivity : BaseActivity<ActivityAttendanceBinding>(R.layout.activity_attendance) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PilatesApp()
        }

    }
}