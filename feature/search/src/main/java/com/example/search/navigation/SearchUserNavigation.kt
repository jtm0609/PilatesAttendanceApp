package com.example.search.navigation

import android.content.Context
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.core_android.navigation.Route
import com.example.search.SearchUserScreen
import com.example.search.SearchUserViewModel

fun NavGraphBuilder.searchUserNavGraph(
    navController: NavHostController,
    context: Context
) {
    composable<Route.SearchUser> {
        val searchUserViewModel: SearchUserViewModel = hiltViewModel()
        val keyboardController = LocalSoftwareKeyboardController.current
        val hideKeyboard: () -> Unit = {
            keyboardController?.hide()
        }
        SearchUserScreen(
            navController = navController,
            viewModel = searchUserViewModel,
            context = context,
            hideKeyboard = hideKeyboard
        )
    }
}