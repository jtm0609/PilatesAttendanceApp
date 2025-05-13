package com.example.main

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val context : Context = LocalContext.current
    Scaffold {
        MainNaviHost(
            navController = navController,
            context = context,
            modifier = Modifier.padding(it)
        )
    }
}