package com.example.attendance.complete.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.attendance.complete.AttendanceCompleteScreen
import com.example.core_android.navigation.Route

fun NavGraphBuilder.attendanceCompleteNavGraph(
    navController: NavHostController
) {
    composable<Route.AttendanceComplete> {
        AttendanceCompleteScreen(
            navController = navController
        )
    }
}