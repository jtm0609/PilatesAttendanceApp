package com.example.registration.reregister.navigation

import android.content.Context
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.core_android.navigation.Route
import com.example.registration.reregister.ReRegisterUserScreen
import com.example.registration.reregister.ReRegisterUserViewModel
import com.example.search.SearchUserViewModel

fun NavGraphBuilder.reRegisterUserNavGraph(
    navController: NavHostController,
    context: Context
) {
    composable<Route.ReRegister>(
        typeMap = Route.ReRegister.typeMap
    ) {
        val viewModel: ReRegisterUserViewModel = hiltViewModel()
        val searchUserViewModel: SearchUserViewModel = hiltViewModel(navController.getBackStackEntry(Route.SearchUser))
        ReRegisterUserScreen(
            navController = navController,
            viewModel = viewModel,
            searchUserViewModel = searchUserViewModel,
            context = context
        )
    }
}