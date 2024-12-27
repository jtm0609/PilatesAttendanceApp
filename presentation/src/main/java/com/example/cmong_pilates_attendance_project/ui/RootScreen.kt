package com.example.cmong_pilates_attendance_project.ui

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.cmong_pilates_attendance_project.ui.navigation.PilatesAppNaviHost


@Composable
fun RootScreen() {
    val navController = rememberNavController()
    val context : Context = LocalContext.current
    Scaffold() {
        PilatesAppNaviHost(
            navController = navController,
            context = context,
            modifier = Modifier.padding(it)
        )
    }
}