package com.example.presentation.ui.feature.admin.manageuser

import androidx.compose.foundation.layout.Column
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
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF2b2b2b),
        topBar = {
            Toolbar(
                navController = navController,
                titleText = stringResource(id = R.string.title_manage_user)
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(it)
            ) {
                Divider(
                    color = Color(0xFF333333),
                    thickness = 1.dp,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Column {
                    Menu(
                        text = stringResource(id = R.string.text_menu_re_register_user),
                        onClick = {
                            navController.navigate(
                                AppDestination.ReRegister(userViewModel.searchedUser)
                            )
                        }
                    )
                    Menu(
                        text = stringResource(id = R.string.text_menu_change_mileage),
                        onClick = {
                            navController.navigate(
                                AppDestination.ChangeUserMileage(
                                    userViewModel.searchedUser
                                )
                            )
                        }
                    )
                }
            }
        }
    )
}