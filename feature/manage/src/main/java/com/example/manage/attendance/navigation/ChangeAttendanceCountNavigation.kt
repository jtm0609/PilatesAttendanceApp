package com.example.manage.attendance.navigation

import android.content.Context
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.core_android.navigation.Route
import com.example.manage.attendance.ChangeAttendanceCountScreen
import com.example.manage.attendance.ChangeAttendanceCountViewModel

fun NavGraphBuilder.changeAttendanceCountNavGraph(
    navController: NavHostController,
    context: Context
) {
    composable<Route.ChangeAttendanceCount> {
        val viewModel: ChangeAttendanceCountViewModel = hiltViewModel()
        ChangeAttendanceCountScreen(
            navController = navController,
            viewModel = viewModel,
            context = context
        )
    }
}