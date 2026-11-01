package com.example.member.manage.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.navigation.Route
import com.example.member.manage.UserManageScreen

fun NavGraphBuilder.userManageNavGraph(
    navController: NavHostController
) {
    composable<Route.ManageUser> {
        UserManageScreen(
            navController = navController
        )
    }
}