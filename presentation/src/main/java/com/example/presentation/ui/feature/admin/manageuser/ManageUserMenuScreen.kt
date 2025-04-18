package com.example.presentation.ui.feature.admin.manageuser

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.presentation.R
import com.example.presentation.navigation.AppDestination
import com.example.presentation.ui.component.Menu
import com.example.presentation.ui.component.Toolbar

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ManageUserMenuScreen(
    navController: NavHostController,
    userViewModel: UserViewModel
) {
    UserManagementScreen(
        navController = navController,
        onReRegisterClick = {
            navController.navigate(
                AppDestination.ReRegister(userViewModel.searchedUser)
            )
        },
        onChangeMileageClick = {
            navController.navigate(
                AppDestination.ChangeUserMileage(userViewModel.searchedUser)
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserManagementScreen(
    navController: NavHostController,
    onReRegisterClick: () -> Unit,
    onChangeMileageClick: () -> Unit
) {
    val backgroundColor = Color(0xFF2b2b2b)
    val dividerColor = Color(0xFF333333)
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = backgroundColor,
        topBar = {
            Toolbar(
                navController = navController,
                titleText = stringResource(id = R.string.title_manage_user)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Divider(
                color = dividerColor,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            )

            ManagementOptions(
                onReRegisterClick = onReRegisterClick,
                onChangeMileageClick = onChangeMileageClick
            )
        }
    }
}

@Composable
private fun ManagementOptions(
    onReRegisterClick: () -> Unit,
    onChangeMileageClick: () -> Unit
) {
    Column {
        // 재등록 메뉴
        MenuOption(
            textRes = R.string.text_menu_re_register_user,
            onClick = onReRegisterClick
        )
        
        // 마일리지 변경 메뉴
        MenuOption(
            textRes = R.string.text_menu_change_mileage,
            onClick = onChangeMileageClick
        )
    }
}

@Composable
private fun MenuOption(
    textRes: Int,
    onClick: () -> Unit
) {
    Menu(
        text = stringResource(id = textRes),
        onClick = onClick
    )
}