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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.designsystem.component.Menu
import com.example.designsystem.component.Toolbar
import com.example.designsystem.theme.DarkGray
import com.example.designsystem.theme.LightGray
import com.example.feature.setting.R
import com.example.search.SearchUserViewModel

@Preview
@Composable
fun UserManageSettingScreen(
    navController: NavHostController,
    searchUserViewModel: SearchUserViewModel = hiltViewModel(navController.getBackStackEntry(com.example.navigation.Route.SearchUser))
) {
    UserSettingContent(
        navController = navController,
        onReRegisterClick = {
            navController.navigate(
                com.example.navigation.Route.ReRegister(searchUserViewModel.searchedUser)
            )
        },
        onChangeMileageClick = {
            navController.navigate(
                com.example.navigation.Route.ChangeUserMileage(searchUserViewModel.searchedUser)
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserSettingContent(
    navController: NavHostController,
    onReRegisterClick: () -> Unit,
    onChangeMileageClick: () -> Unit
) {
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = DarkGray,
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
                color = LightGray,
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
        MenuOption(
            textRes = R.string.text_menu_re_register_user,
            onClick = onReRegisterClick
        )

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