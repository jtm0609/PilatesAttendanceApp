package com.example.search.navigation

import android.content.Context
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.navigation.Route
import com.example.search.SearchUserScreen

fun NavGraphBuilder.searchUserNavGraph(
    navController: NavHostController,
    context: Context
) {
    composable<Route.SearchUser> {
        val keyboardController = LocalSoftwareKeyboardController.current
        val hideKeyboard: () -> Unit = {
            keyboardController?.hide()
        }
        SearchUserScreen(
            navController = navController,
            context = context,
            hideKeyboard = hideKeyboard
        )
    }
}