package com.example.member.register.navigation

import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.navigation.Route
import com.example.member.register.RegisterUserScreen

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