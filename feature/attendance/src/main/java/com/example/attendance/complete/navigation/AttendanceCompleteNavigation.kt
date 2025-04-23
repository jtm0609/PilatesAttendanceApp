package com.example.attendance.complete.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.attendance.complete.AttendanceCompleteScreen
import com.example.attendance.complete.AttendanceCompleteViewModel
import com.example.core_android.navigation.Route

fun NavGraphBuilder.attendanceCompleteNavGraph(
    navController: NavHostController
) {
    composable<Route.AttendanceComplete> {
        val viewModel: AttendanceCompleteViewModel = hiltViewModel()
        AttendanceCompleteScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}