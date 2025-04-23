package com.example.registration.register.navigation

import android.content.Context
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.core_android.navigation.Route
import com.example.registration.register.RegisterUserScreen
import com.example.registration.register.RegisterUserViewModel

fun NavGraphBuilder.registerUserNavGraph(
    navController: NavHostController,
    context: Context
) {
    composable<Route.RegisterUser> {
        val viewModel: RegisterUserViewModel = hiltViewModel()
        RegisterUserScreen(
            navController = navController,
            viewModel = viewModel,
            context = context
        )
    }
}