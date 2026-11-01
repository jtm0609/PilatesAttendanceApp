package com.example.member.manage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.designsystem.component.MenuCard
import com.example.designsystem.component.PilatesScaffold
import com.example.designsystem.theme.PilatesTheme
import com.example.feature.member.R
import com.example.member.component.MemberSummary
import com.example.member.search.SearchUserViewModel
import com.example.navigation.Route

@Composable
fun UserManageScreen(
    navController: NavHostController,
    searchUserViewModel: SearchUserViewModel = hiltViewModel(navController.getBackStackEntry(Route.SearchUser))
) {
    val user = searchUserViewModel.searchedUser

    UserManageContent(
        memberName = user.name,
        memberPhone = user.phoneNumber,
        memberMileage = user.mileage,
        onBack = { navController.popBackStack() },
        onReRegisterClick = {
            navController.navigate(Route.ReRegister(searchUserViewModel.searchedUser))
        },
        onChangeMileageClick = {
            navController.navigate(Route.ChangeUserMileage(searchUserViewModel.searchedUser))
        }
    )
}

@Composable
private fun UserManageContent(
    memberName: String,
    memberPhone: String,
    memberMileage: Int,
    onBack: () -> Unit,
    onReRegisterClick: () -> Unit,
    onChangeMileageClick: () -> Unit
) {
    PilatesScaffold(
        title = stringResource(id = R.string.title_manage_user),
        centerVertically = true,
        onBack = onBack
    ) {
        MemberSummary(
            name = memberName,
            phone = memberPhone,
            mileage = memberMileage
        )

        Spacer(modifier = Modifier.height(20.dp))

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
                title = stringResource(R.string.text_menu_re_register_user),
                description = stringResource(R.string.text_menu_re_register_user_desc),
                icon = Icons.Filled.Refresh,
                onClick = onReRegisterClick,
            )
            MenuCard(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                title = stringResource(R.string.text_menu_change_mileage),
                description = stringResource(R.string.text_menu_change_mileage_desc),
                icon = Icons.Filled.Star,
                onClick = onChangeMileageClick,
            )
        }
    }
}

@Preview(device = Devices.TABLET)
@Composable
private fun UserManagePreview() {
    PilatesTheme {
        UserManageContent(
            memberName = "김하늘",
            memberPhone = "01012345678",
            memberMileage = 12,
            onBack = {},
            onReRegisterClick = {},
            onChangeMileageClick = {}
        )
    }
}
