package com.example.main

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.admin.attendance.navigation.changeAttendanceCountNavGraph
import com.example.admin.menu.navigation.adminMenuNavGraph
import com.example.attendance.complete.navigation.attendanceCompleteNavGraph
import com.example.attendance.home.navigation.attendanceHomeNavGraph
import com.example.member.manage.navigation.userManageNavGraph
import com.example.member.mileage.navigation.changeUserMileageNavGraph
import com.example.member.register.navigation.registerUserNavGraph
import com.example.member.reregister.navigation.reRegisterUserNavGraph
import com.example.member.search.navigation.searchUserNavGraph
import com.example.navigation.Route

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

        adminMenuNavGraph(navController)

        changeAttendanceCountNavGraph(navController, context)

        changeUserMileageNavGraph(navController, context)

        searchUserNavGraph(navController, context)

        userManageNavGraph(navController)

        registerUserNavGraph(navController, context)

        reRegisterUserNavGraph(navController, context)
    }
}