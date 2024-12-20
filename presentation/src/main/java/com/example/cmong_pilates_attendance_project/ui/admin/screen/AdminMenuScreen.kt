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
import androidx.navigation.fragment.findNavController
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.utils.Constant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun toolbar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.title_admin_menu),
                color = Color.White,
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF2b2b2b)),
        navigationIcon = {
            IconButton(onClick = {activity?.finish()}) {
                Icon(
                    Icons.Filled.ArrowBack, "backIcon", tint = Color.White,
                    modifier = Modifier.size(50.dp).padding(end = 10.dp)
                )
            }
        },
    )
}

@Composable
fun menuItem(clickItem: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                color = Color(0xFF333333),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(6.dp).clickable { movePage(clickItem) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start=20.dp, end=20.dp,top=10.dp,bottom=10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = getMenuTitle(clickItem),
                color = Color.White,
                fontSize = 22.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier.weight(1f)
            )

            IconButton(
                onClick = {
                    movePage(clickItem)
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "backIcon",
                    tint = Color.White
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AdminMenuScreen() {
    Scaffold(
        containerColor = Color(0xFF2b2b2b),
        topBar = { toolbar() },
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
                    menuItem(clickItem = Constant.REGISTER_MENU_CLICK)
                    menuItem(clickItem = Constant.MANAGE_USER_MENU_CLICK)
                    menuItem(clickItem = Constant.CHANGE_ATTENDANCE_MENU_CLICK)
                }
            }
        }
    )
}

private fun movePage(menuItem: Int) {
    when (menuItem) {
        Constant.REGISTER_MENU_CLICK -> {
            findNavController().navigate(R.id.action_adminMenuFragment_to_registerUserFragment)
        }

        Constant.MANAGE_USER_MENU_CLICK -> {
            findNavController().navigate(R.id.action_adminMenuFragment_to_inputPhoneNumberFragment)
        }

        else -> {
            findNavController().navigate(R.id.action_adminMenuFragment_to_changeAttendanceCountFragment)
        }
    }
}

private fun getMenuTitle(menuItem: Int): String {
    val menuTitle = when(menuItem) {
        Constant.REGISTER_MENU_CLICK -> {
            getString(R.string.text_menu_register_user)
        }

        Constant.MANAGE_USER_MENU_CLICK -> {
            getString(R.string.text_menu_manage_user)
        }

        else -> {
            getString(R.string.text_menu_change_attendance_count)
        }
    }
    return menuTitle
}