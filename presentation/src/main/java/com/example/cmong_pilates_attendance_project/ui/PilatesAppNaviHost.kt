package com.example.cmong_pilates_attendance_project.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cmong_pilates_attendance_project.ui.admin.screen.AdminMenuScreen
import com.example.cmong_pilates_attendance_project.ui.admin.screen.ChangeAttendanceCountScreen
import com.example.cmong_pilates_attendance_project.ui.admin.screen.ChangeUserMileageScreen
import com.example.cmong_pilates_attendance_project.ui.admin.screen.InputPhoneNumberScreen
import com.example.cmong_pilates_attendance_project.ui.admin.screen.ManageUserScreen
import com.example.cmong_pilates_attendance_project.ui.admin.screen.RegisterUserScreen
import com.example.cmong_pilates_attendance_project.ui.admin.screen.ReregisterUserScreen
import com.example.cmong_pilates_attendance_project.ui.attendance.screen.AttendanceCompleteScreen
import com.example.cmong_pilates_attendance_project.ui.attendance.screen.AttendanceMainScreen


@Composable
fun PilatesAppNaviHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = PilatesAppScreen.AttendanceMain.name,
        modifier = modifier
    ){

        composable(PilatesAppScreen.AttendanceMain.name){
            AttendanceMainScreen(navController)
        }

        composable(PilatesAppScreen.AttendanceComplete.name){
            AttendanceCompleteScreen(navController)
        }

        composable(PilatesAppScreen.AdminMenu.name){
            AdminMenuScreen(navController)
        }

        composable(PilatesAppScreen.ChangeAttendanceCount.name){
            ChangeAttendanceCountScreen(navController)
        }

        composable(PilatesAppScreen.ChangeUserMileage.name){
            ChangeUserMileageScreen(navController)
        }

        composable(PilatesAppScreen.InputPhoneNumber.name){
            InputPhoneNumberScreen(navController)
        }

        composable(PilatesAppScreen.ManageUser.name){
            ManageUserScreen(navController)
        }

        composable(PilatesAppScreen.RegisterUser.name){
            RegisterUserScreen(navController = navController)
        }

        composable(PilatesAppScreen.ReregisterUser.name){
            ReregisterUserScreen(navController)
        }


    }
}