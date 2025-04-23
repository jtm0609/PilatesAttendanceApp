package com.example.main

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.attendance.complete.navigation.attendanceCompleteNavGraph
import com.example.attendance.home.navigation.attendanceHomeNavGraph
import com.example.core_android.navigation.Route
import com.example.manage.attendance.navigation.changeAttendanceCountNavGraph
import com.example.manage.mileage.navigation.changeUserMileageNavGraph
import com.example.registration.register.navigation.registerUserNavGraph
import com.example.registration.reregister.navigation.reRegisterUserNavGraph
import com.example.search.SearchUserViewModel
import com.example.search.navigation.searchUserNavGraph
import com.example.setting.navigation.adminSettingNavGraph
import com.example.setting.navigation.userManageNavGraph


@Composable
fun MainNaviHost(
    navController: NavHostController,
    context: Context,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Route.AttendanceMain,
        modifier = modifier
    ) {

        attendanceHomeNavGraph(navController, context)

        attendanceCompleteNavGraph(navController)

        adminSettingNavGraph(navController)

        changeAttendanceCountNavGraph(navController, context)

        changeUserMileageNavGraph(navController, context)

        searchUserNavGraph(navController, context)

        userManageNavGraph(navController)

        registerUserNavGraph(navController, context)

        reRegisterUserNavGraph(navController, context)
    }
}