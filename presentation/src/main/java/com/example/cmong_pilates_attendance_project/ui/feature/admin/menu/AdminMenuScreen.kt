package com.example.cmong_pilates_attendance_project.ui.feature.admin.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.ui.navigation.AppScreen
import com.example.cmong_pilates_attendance_project.ui.component.Menu
import com.example.cmong_pilates_attendance_project.ui.component.Toolbar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminMenuScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF2b2b2b),
        topBar = {
            Toolbar(
                navController = navController,
                titleText = stringResource(id = R.string.title_admin_menu)
            )
        },
        content = {

            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
            ) {
                Divider(
                    color = Color(0xFF333333),
                    thickness = 1.dp,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Column {
                    Menu(
                        text = stringResource(id = R.string.text_menu_register_user),
                        onClick = { navController.navigate(AppScreen.RegisterUser.name) }
                    )
                    Menu(
                        text = stringResource(id = R.string.text_menu_manage_user),
                        onClick = { navController.navigate(AppScreen.SearchUser.name) }
                    )
                    Menu(
                        text = stringResource(id = R.string.text_menu_change_attendance_count),
                        onClick = { navController.navigate(AppScreen.ChangeAttendanceCount.name) }
                    )
                }
            }
        }
    )
}