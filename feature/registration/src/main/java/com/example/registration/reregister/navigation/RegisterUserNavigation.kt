package com.example.registration.reregister.navigation

import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.navigation.Route
import com.example.registration.reregister.ReRegisterUserScreen

fun NavGraphBuilder.reRegisterUserNavGraph(
    navController: NavHostController,
    context: Context
) {
    composable<com.example.navigation.Route.ReRegister>(
        typeMap = com.example.navigation.Route.ReRegister.typeMap
    ) {
        ReRegisterUserScreen(
            navController = navController,
            context = context
        )
    }
}