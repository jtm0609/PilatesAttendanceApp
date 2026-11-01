package com.example.admin.menu.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.navigation.Route
import com.example.admin.menu.AdminMenuScreen

fun NavGraphBuilder.adminMenuNavGraph(
    navController: NavHostController
) {
    composable<Route.AdminMenu> {
        AdminMenuScreen(
            navController = navController
        )
    }
}