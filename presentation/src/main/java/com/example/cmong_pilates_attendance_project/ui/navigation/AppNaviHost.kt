package com.example.cmong_pilates_attendance_project.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cmong_pilates_attendance_project.ui.feature.admin.manageuser.attendancecount.ChangeAttendanceCountScreen
import com.example.cmong_pilates_attendance_project.ui.feature.admin.manageuser.attendancecount.ChangeAttendanceCountViewModel
import com.example.cmong_pilates_attendance_project.ui.feature.admin.manageuser.mileage.ChangeUserMileageScreen
import com.example.cmong_pilates_attendance_project.ui.feature.admin.manageuser.mileage.ChangeUserMileageViewModel
import com.example.cmong_pilates_attendance_project.ui.feature.admin.menu.AdminMenuScreen
import com.example.cmong_pilates_attendance_project.ui.feature.admin.manageuser.ManageUserMenuScreen
import com.example.cmong_pilates_attendance_project.ui.feature.admin.register.RegisterUserScreen
import com.example.cmong_pilates_attendance_project.ui.feature.admin.register.RegisterUserViewModel
import com.example.cmong_pilates_attendance_project.ui.feature.admin.manageuser.reregister.ReregisterUserScreen
import com.example.cmong_pilates_attendance_project.ui.feature.admin.manageuser.reregister.ReregisterUserViewModel
import com.example.cmong_pilates_attendance_project.ui.feature.admin.manageuser.search.SearchUserScreen
import com.example.cmong_pilates_attendance_project.ui.feature.admin.manageuser.search.SearchUserViewModel
import com.example.cmong_pilates_attendance_project.ui.feature.attendance.complete.AttendanceCompleteScreen
import com.example.cmong_pilates_attendance_project.ui.feature.attendance.complete.AttendanceCompleteViewModel
import com.example.cmong_pilates_attendance_project.ui.feature.attendance.main.AttendanceMainScreen
import com.example.cmong_pilates_attendance_project.ui.feature.attendance.main.AttendanceViewModel
import com.example.cmong_pilates_attendance_project.ui.navigation.NavArgument.ARG_USER_MILEAGE
import com.example.cmong_pilates_attendance_project.ui.navigation.NavArgument.ARG_USER_NAME
import com.example.cmong_pilates_attendance_project.ui.feature.admin.manageuser.UserViewModel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PilatesAppNaviHost(
    navController: NavHostController,
    context: Context,
    modifier: Modifier,
) {
    val userViewModel: UserViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = AppScreen.AttendanceMain.name,
        modifier = modifier
    ) {

        composable(AppScreen.AttendanceMain.name) {
            val viewModel: AttendanceViewModel = hiltViewModel()
            AttendanceMainScreen(
                navController = navController,
                viewModel = viewModel,
                context = context
            )
        }

        composable(
            route = "${AppScreen.AttendanceComplete}/{$ARG_USER_NAME}/{$ARG_USER_MILEAGE}",
            arguments = listOf(
                navArgument(ARG_USER_NAME) { type = NavType.StringType },
                navArgument(ARG_USER_MILEAGE) { type = NavType.IntType }
            )
        ) {
            val viewModel: AttendanceCompleteViewModel = hiltViewModel()
            AttendanceCompleteScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(AppScreen.AdminMenu.name) {
            AdminMenuScreen(
                navController = navController
            )
        }

        composable(AppScreen.ChangeAttendanceCount.name) {
            val viewModel: ChangeAttendanceCountViewModel = hiltViewModel()
            ChangeAttendanceCountScreen(
                navController = navController,
                viewModel = viewModel,
                context = context
            )
        }

        composable(AppScreen.ChangeUserMileage.name) {
            val viewModel: ChangeUserMileageViewModel = hiltViewModel()
            ChangeUserMileageScreen(
                navController = navController,
                viewModel = viewModel,
                userViewModel = userViewModel,
                context = context
            )
        }

        composable(AppScreen.SearchUser.name) {
            val viewModel: SearchUserViewModel = hiltViewModel()
            val keyboardController = LocalSoftwareKeyboardController.current
            val hideKeyboard: () -> Unit = {
                keyboardController?.hide()
            }
            SearchUserScreen(
                navController = navController,
                viewModel = viewModel,
                userViewModel = userViewModel,
                context = context,
                hideKeyboard = hideKeyboard
            )
        }

        composable(AppScreen.ManageUser.name) {
            ManageUserMenuScreen(
                navController = navController
            )
        }

        composable(AppScreen.RegisterUser.name) {
            val viewModel: RegisterUserViewModel = hiltViewModel()
            RegisterUserScreen(
                navController = navController,
                viewModel = viewModel,
                context = context
            )
        }

        composable(AppScreen.ReregisterUser.name) {
            val viewModel: ReregisterUserViewModel = hiltViewModel()
            ReregisterUserScreen(
                navController = navController,
                viewModel = viewModel,
                userViewModel = userViewModel,
                context = context
            )
        }
    }
}