package com.example.registration.register.navigation

import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.core_android.navigation.Route
import com.example.registration.register.RegisterUserScreen

fun NavGraphBuilder.registerUserNavGraph(
    navController: NavHostController,
    context: Context
) {
    composable<Route.RegisterUser> {
        RegisterUserScreen(
            navController = navController,
            context = context
        )
    }
}