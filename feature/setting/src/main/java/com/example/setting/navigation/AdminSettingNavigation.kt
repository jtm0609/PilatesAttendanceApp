package com.example.setting.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.core_android.navigation.Route
import com.example.setting.AdminSettingScreen

fun NavGraphBuilder.adminSettingNavGraph(
    navController: NavHostController
) {
    composable<Route.AdminMenu> {
        AdminSettingScreen(
            navController = navController
        )
    }
}