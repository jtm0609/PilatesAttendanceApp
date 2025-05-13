package com.example.designsystem.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.designsystem.theme.DarkGray
import com.example.designsystem.theme.Typography
import com.example.designsystem.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(navController: NavHostController, titleText: String) {
    TopAppBar(
        title = {
            Text(
                text = titleText,
                color = White,
                style = Typography.headlineLargeSB,
                textAlign = TextAlign.Center
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = DarkGray),
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    Icons.Filled.ArrowBack, "backIcon", tint = White,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 10.dp)
                )
            }
        },
    )
}