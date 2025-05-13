package com.example.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.designsystem.component.Menu
import com.example.designsystem.component.Toolbar
import com.example.designsystem.theme.DarkGray
import com.example.feature.setting.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminSettingScreen(navController: NavHostController) {
    AdminSettingContent(
        navController = navController,
        onRegisterUserClick = { 
            navController.navigate(com.example.navigation.Route.RegisterUser)
        },
        onManageUserClick = { 
            navController.navigate(com.example.navigation.Route.SearchUser)
        },
        onChangeAttendanceCountClick = { 
            navController.navigate(com.example.navigation.Route.ChangeAttendanceCount)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AdminSettingContent(
    navController: NavHostController,
    onRegisterUserClick: () -> Unit,
    onManageUserClick: () -> Unit,
    onChangeAttendanceCountClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = DarkGray,
        topBar = {
            Toolbar(
                navController = navController,
                titleText = stringResource(id = R.string.title_admin_menu)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Divider(
                color = DarkGray,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            )

            AdminMenuOptions(
                onRegisterUserClick = onRegisterUserClick,
                onManageUserClick = onManageUserClick,
                onChangeAttendanceCountClick = onChangeAttendanceCountClick
            )
        }
    }
}

@Composable
private fun AdminMenuOptions(
    onRegisterUserClick: () -> Unit,
    onManageUserClick: () -> Unit,
    onChangeAttendanceCountClick: () -> Unit
) {
    Column {
        // 회원 등록 메뉴
        AdminMenuItem(
            textRes = R.string.text_menu_register_user,
            onClick = onRegisterUserClick
        )
        
        // 회원 관리 메뉴
        AdminMenuItem(
            textRes = R.string.text_menu_manage_user,
            onClick = onManageUserClick
        )
        
        // 출석 횟수 변경 메뉴
        AdminMenuItem(
            textRes = R.string.text_menu_change_attendance_count,
            onClick = onChangeAttendanceCountClick
        )
    }
}

@Composable
private fun AdminMenuItem(
    textRes: Int,
    onClick: () -> Unit
) {
    Menu(
        text = stringResource(id = textRes),
        onClick = onClick
    )
}