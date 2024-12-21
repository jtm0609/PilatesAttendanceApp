package com.example.cmong_pilates_attendance_project.ui.admin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cmong_pilates_attendance_project.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
    }
}