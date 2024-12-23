package com.example.cmong_pilates_attendance_project.ui.admin.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.fragment.findNavController
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.ui.PilatesAppScreen
import com.example.cmong_pilates_attendance_project.ui.component.MenuItem
import com.example.cmong_pilates_attendance_project.ui.component.Toolbar
import com.example.cmong_pilates_attendance_project.utils.Constant



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminMenuScreen(navController: NavHostController) {
    Scaffold(
        containerColor = Color(0xFF2b2b2b),
        topBar = { Toolbar(navController = navController, titleText = stringResource(id = R.string.title_admin_menu)) },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
            ) {
                Divider(
                    color = Color(0xFF333333),
                    thickness = 1.dp,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Column {
                    MenuItem(
                        text = stringResource(id = R.string.text_menu_register_user),
                        onClick = { navController.navigate(PilatesAppScreen.RegisterUser.name) }
                    )
                    MenuItem(
                        text = stringResource(id = R.string.text_menu_manage_user),
                        onClick = { navController.navigate(PilatesAppScreen.InputPhoneNumber.name) }
                    )
                    MenuItem(
                        text = stringResource(id = R.string.text_menu_change_attendance_count),
                        onClick = { navController.navigate(PilatesAppScreen.ChangeAttendanceCount.name) }
                    )
                }
            }
        }
    )
}