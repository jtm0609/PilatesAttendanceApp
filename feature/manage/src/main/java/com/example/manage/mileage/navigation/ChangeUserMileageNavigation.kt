package com.example.manage.mileage.navigation

import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.navigation.Route
import com.example.manage.mileage.ChangeUserMileageScreen

fun NavGraphBuilder.changeUserMileageNavGraph(
    navController: NavHostController,
    context: Context
) {
    composable<com.example.navigation.Route.ChangeUserMileage>(
        typeMap = com.example.navigation.Route.ChangeUserMileage.typeMap
    ) {
        ChangeUserMileageScreen(
            navController = navController,
            context = context
        )
    }
}