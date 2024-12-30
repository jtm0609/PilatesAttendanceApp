package com.example.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.presentation.ui.feature.admin.manageuser.attendancecount.ChangeAttendanceCountScreen
import com.example.presentation.ui.feature.admin.manageuser.attendancecount.ChangeAttendanceCountViewModel
import com.example.presentation.ui.feature.admin.manageuser.mileage.ChangeUserMileageScreen
import com.example.presentation.ui.feature.admin.manageuser.mileage.ChangeUserMileageViewModel
import com.example.presentation.ui.feature.admin.menu.AdminMenuScreen
import com.example.presentation.ui.feature.admin.manageuser.ManageUserMenuScreen
import com.example.presentation.ui.feature.admin.register.RegisterUserScreen
import com.example.presentation.ui.feature.admin.register.RegisterUserViewModel
import com.example.presentation.ui.feature.admin.manageuser.reregister.ReRegisterUserScreen
import com.example.presentation.ui.feature.admin.manageuser.reregister.ReRegisterUserViewModel
import com.example.presentation.ui.feature.admin.manageuser.search.SearchUserScreen
import com.example.presentation.ui.feature.admin.manageuser.search.SearchUserViewModel
import com.example.presentation.ui.feature.attendance.complete.AttendanceCompleteScreen
import com.example.presentation.ui.feature.attendance.complete.AttendanceCompleteViewModel
import com.example.presentation.ui.feature.attendance.main.AttendanceMainScreen
import com.example.presentation.ui.feature.attendance.main.AttendanceViewModel
import com.example.presentation.ui.feature.admin.manageuser.UserViewModel


@Composable
fun PilatesAppNaviHost(
    navController: NavHostController,
    context: Context,
    modifier: Modifier,
) {
    val userViewModel: UserViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = AppDestination.AttendanceMain,
        modifier = modifier
    ) {

        composable<AppDestination.AttendanceMain> {
            val viewModel: AttendanceViewModel = hiltViewModel()
            AttendanceMainScreen(
                navController = navController,
                viewModel = viewModel,
                context = context
            )
        }

        composable<AppDestination.AttendanceComplete> {
            val viewModel: AttendanceCompleteViewModel = hiltViewModel()
            AttendanceCompleteScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable<AppDestination.AdminMenu> {
            AdminMenuScreen(
                navController = navController
            )
        }

        composable<AppDestination.ChangeAttendanceCount> {
            val viewModel: ChangeAttendanceCountViewModel = hiltViewModel()
            ChangeAttendanceCountScreen(
                navController = navController,
                viewModel = viewModel,
                context = context
            )
        }

        composable<AppDestination.ChangeUserMileage>(
            typeMap = AppDestination.ChangeUserMileage.typeMap
        ) {
            val viewModel: ChangeUserMileageViewModel = hiltViewModel()
            ChangeUserMileageScreen(
                navController = navController,
                viewModel = viewModel,
                userViewModel = userViewModel,
                context = context
            )
        }

        composable<AppDestination.SearchUser> {
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

        composable<AppDestination.ManageUser> {
            ManageUserMenuScreen(
                navController = navController,
                userViewModel = userViewModel
            )
        }

        composable<AppDestination.RegisterUser> {
            val viewModel: RegisterUserViewModel = hiltViewModel()
            RegisterUserScreen(
                navController = navController,
                viewModel = viewModel,
                context = context
            )
        }

        composable<AppDestination.ReRegister>(
            typeMap = AppDestination.ReRegister.typeMap
        ) {
            val viewModel: ReRegisterUserViewModel = hiltViewModel()
            ReRegisterUserScreen(
                navController = navController,
                viewModel = viewModel,
                userViewModel = userViewModel,
                context = context
            )
        }
    }
}