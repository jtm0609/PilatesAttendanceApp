package com.example.cmong_pilates_attendance_project.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DiApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}