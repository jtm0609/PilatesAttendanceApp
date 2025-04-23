package com.example.manage.mileage.navigation

import android.content.Context
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.core_android.navigation.Route
import com.example.manage.mileage.ChangeUserMileageScreen
import com.example.manage.mileage.ChangeUserMileageViewModel
import com.example.search.SearchUserViewModel

fun NavGraphBuilder.changeUserMileageNavGraph(
    navController: NavHostController,
    context: Context
) {
    composable<Route.ChangeUserMileage>(
        typeMap = Route.ChangeUserMileage.typeMap
    ) {
        val searchUserViewModel: SearchUserViewModel = hiltViewModel(navController.getBackStackEntry(Route.SearchUser))
        val viewModel: ChangeUserMileageViewModel = hiltViewModel()
        ChangeUserMileageScreen(
            navController = navController,
            viewModel = viewModel,
            searchUserViewModel = searchUserViewModel,
            context = context
        )
    }
}