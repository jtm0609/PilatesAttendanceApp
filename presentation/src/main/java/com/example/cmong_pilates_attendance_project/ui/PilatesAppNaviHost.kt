package com.example.cmong_pilates_attendance_project.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
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
import com.example.cmong_pilates_attendance_project.viewmodel.AdminViewModel
import com.example.cmong_pilates_attendance_project.viewmodel.AttendanceViewModel
import com.example.cmong_pilates_attendance_project.viewmodel.ChangeAttendanceCountViewModel
import com.example.cmong_pilates_attendance_project.viewmodel.ChangeUserMileageViewModel
import com.example.cmong_pilates_attendance_project.viewmodel.RegisterUserViewModel
import com.example.cmong_pilates_attendance_project.viewmodel.ReregisterUserViewModel
import com.example.cmong_pilates_attendance_project.viewmodel.UserViewModel


@Composable
fun PilatesAppNaviHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val adminViewModel : AdminViewModel = hiltViewModel()
    val attendanceViewModel : AttendanceViewModel = hiltViewModel()
    val userViewModel : UserViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = PilatesAppScreen.AttendanceMain.name,
        modifier = modifier
    ){

        composable(PilatesAppScreen.AttendanceMain.name){
            val viewModel : AttendanceViewModel = hiltViewModel()
            AttendanceMainScreen(
                navController = navController,
                viewModel = viewModel,
                adminViewModel = adminViewModel
            )
        }

        composable(PilatesAppScreen.AttendanceComplete.name){
            AttendanceCompleteScreen(
                navController = navController,
                attendanceViewModel = attendanceViewModel
            )
        }

        composable(PilatesAppScreen.AdminMenu.name){
            AdminMenuScreen(
                navController = navController
            )
        }

        composable(PilatesAppScreen.ChangeAttendanceCount.name){
            val viewModel: ChangeAttendanceCountViewModel = hiltViewModel()
            ChangeAttendanceCountScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(PilatesAppScreen.ChangeUserMileage.name){
            val viewModel : ChangeUserMileageViewModel = hiltViewModel()
            ChangeUserMileageScreen(
                navController = navController,
                viewModel = viewModel,
                userViewModel = userViewModel
            )
        }

        composable(PilatesAppScreen.InputPhoneNumber.name){
            InputPhoneNumberScreen(
                navController = navController,
                userViewModel = userViewModel
            )
        }

        composable(PilatesAppScreen.ManageUser.name){
            ManageUserScreen(
                navController = navController
            )
        }

        composable(PilatesAppScreen.RegisterUser.name){
            val viewModel : RegisterUserViewModel = hiltViewModel()
            RegisterUserScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(PilatesAppScreen.ReregisterUser.name){
            val viewModel : ReregisterUserViewModel = hiltViewModel()
            ReregisterUserScreen(
                navController = navController,
                viewModel = viewModel,
                userViewModel = userViewModel
            )
        }
    }
}