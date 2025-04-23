package com.example.attendance.home.navigation

import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.attendance.home.AttendanceHomeScreen
import com.example.core_android.navigation.Route

fun NavGraphBuilder.attendanceHomeNavGraph(
    navController: NavHostController,
    context: Context,
) {
    composable<Route.AttendanceMain> {
        AttendanceHomeScreen(
            navController = navController,
            context = context
        )
    }
}
