package com.example.setting.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.navigation.Route
import com.example.setting.UserManageSettingScreen

fun NavGraphBuilder.userManageNavGraph(
    navController: NavHostController
) {
    composable<com.example.navigation.Route.ManageUser> {
        UserManageSettingScreen(
            navController = navController
        )
    }
}