package com.example.cmong_pilates_attendance_project

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.example.cmong_pilates_attendance_project.di.DiApplication
import dagger.hilt.android.testing.HiltTestApplication

// A custom runner to set up the instrumented application class for tests.
class CustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, DiApplication::class.java.name, context)
    }
}