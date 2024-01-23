package com.example.cmong_pilates_attendance_project.di

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DiApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Logger.t("JTM")
        Logger.addLogAdapter(AndroidLogAdapter())

    }
}