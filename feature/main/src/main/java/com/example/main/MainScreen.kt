package com.example.main

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.designsystem.theme.PilatesTheme

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val context: Context = LocalContext.current

    PilatesTheme(darkTheme = isSystemInDarkTheme()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PilatesTheme.colors.background)
        ) {
            MainNaviHost(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                context = context,
            )
        }
    }
}
