package com.example.cmong_pilates_attendance_project.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController


@Composable
fun PilatesApp() {
    val navController = rememberNavController()

    Scaffold() {
        PilatesAppNaviHost(
            navController = navController,
            modifier = Modifier.padding(it)
        )
    }
}