package com.example.attendance.home.navigation

import android.content.Context
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.attendance.home.AttendanceHomeScreen
import com.example.attendance.home.AttendanceHomeViewModel
import com.example.core_android.navigation.Route

fun NavGraphBuilder.attendanceHomeNavGraph(
    navController: NavHostController,
    context: Context,
) {
    composable<Route.AttendanceMain> {
        val viewModel: AttendanceHomeViewModel = hiltViewModel()
        AttendanceHomeScreen(
            navController = navController,
            viewModel = viewModel,
            context = context
        )
    }
}
