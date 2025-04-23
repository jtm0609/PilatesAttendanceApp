package com.example.setting.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.core_android.navigation.Route
import com.example.search.SearchUserViewModel
import com.example.setting.UserManageSettingScreen

fun NavGraphBuilder.userManageNavGraph(
    navController: NavHostController
) {
    composable<Route.ManageUser> {
        val searchUserViewModel: SearchUserViewModel = hiltViewModel(navController.getBackStackEntry(Route.SearchUser))
        UserManageSettingScreen(
            navController = navController,
            searchUserViewModel = searchUserViewModel
        )
    }
}