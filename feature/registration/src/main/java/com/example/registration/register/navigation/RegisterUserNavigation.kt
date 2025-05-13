package com.example.registration.register.navigation

import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.navigation.Route
import com.example.registration.register.RegisterUserScreen

fun NavGraphBuilder.registerUserNavGraph(
    navController: NavHostController,
    context: Context
) {
    composable<com.example.navigation.Route.RegisterUser> {
        RegisterUserScreen(
            navController = navController,
            context = context
        )
    }
}