package com.example.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.designsystem.component.PilatesScaffold
import com.example.designsystem.component.MenuCard
import com.example.feature.setting.R
import com.example.navigation.Route

@Composable
fun AdminSettingScreen(navController: NavHostController) {
    AdminSettingContent(
        onBack = { navController.popBackStack() },
        onRegisterUserClick = { navController.navigate(Route.RegisterUser) },
        onManageUserClick = { navController.navigate(Route.SearchUser) },
        onChangeAttendanceCountClick = { navController.navigate(Route.ChangeAttendanceCount) }
    )
}

@Composable
private fun AdminSettingContent(
    onBack: () -> Unit,
    onRegisterUserClick: () -> Unit,
    onManageUserClick: () -> Unit,
    onChangeAttendanceCountClick: () -> Unit
) {
    PilatesScaffold(
        title = stringResource(id = R.string.title_admin_menu),
        centerVertically = true,
        onBack = onBack
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            MenuCard(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                title = stringResource(R.string.text_menu_register_user),
                description = stringResource(R.string.text_menu_register_user_desc),
                icon = Icons.Filled.Add,
                onClick = onRegisterUserClick,
            )
            MenuCard(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                title = stringResource(R.string.text_menu_manage_user),
                description = stringResource(R.string.text_menu_manage_user_desc),
                icon = Icons.Filled.Person,
                onClick = onManageUserClick,
            )
            MenuCard(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                title = stringResource(R.string.text_menu_change_attendance_count),
                description = stringResource(R.string.text_menu_change_attendance_count_desc),
                icon = Icons.Filled.DateRange,
                onClick = onChangeAttendanceCountClick,
            )
        }
    }
}
