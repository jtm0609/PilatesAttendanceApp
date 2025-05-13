package com.example.manage.attendance.navigation

import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.navigation.Route
import com.example.manage.attendance.ChangeAttendanceCountScreen

fun NavGraphBuilder.changeAttendanceCountNavGraph(
    navController: NavHostController,
    context: Context
) {
    composable<com.example.navigation.Route.ChangeAttendanceCount> {
        ChangeAttendanceCountScreen(
            navController = navController,
            context = context
        )
    }
}