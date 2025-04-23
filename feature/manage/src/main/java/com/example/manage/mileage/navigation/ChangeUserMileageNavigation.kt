package com.example.manage.mileage.navigation

import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.core_android.navigation.Route
import com.example.manage.mileage.ChangeUserMileageScreen

fun NavGraphBuilder.changeUserMileageNavGraph(
    navController: NavHostController,
    context: Context
) {
    composable<Route.ChangeUserMileage>(
        typeMap = Route.ChangeUserMileage.typeMap
    ) {
        ChangeUserMileageScreen(
            navController = navController,
            context = context
        )
    }
}