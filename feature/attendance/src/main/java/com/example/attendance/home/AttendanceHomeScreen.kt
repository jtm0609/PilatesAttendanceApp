package com.example.attendance.home

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.attendance.home.component.AdminButton
import com.example.attendance.home.component.IntroSection
import com.example.attendance.home.component.PhoneInputSection
import com.example.common.showToast
import com.example.designsystem.theme.DarkGray
import com.example.navigation.Route

@Composable
fun AttendanceHomeScreen(
    navController: NavHostController,
    viewModel: AttendanceHomeViewModel = hiltViewModel(),
    context: Context
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect

    LaunchedEffect(Unit) {
        effectFlow.collect { effect ->
            when (effect) {
                is AttendanceHomeContract.Effect.GoAdminPage -> {
                    navController.navigate(Route.AdminMenu)
                }

                is AttendanceHomeContract.Effect.ShowToast -> {
                    context.showToast(effect.msg)
                }

                is AttendanceHomeContract.Effect.SuccessAttendance -> {
                    navController.navigate(
                        Route.AttendanceComplete(
                            effect.user.name,
                            effect.user.mileage
                        )
                    )
                }
            }
        }
    }

    AttendanceHomeContent(
        phoneNumber = state.phoneNumber,
        onAdminClick = { viewModel.setEvent(AttendanceHomeContract.Event.OnClickAdminMenu) },
        onNumberClick = { number ->
            viewModel.setEvent(
                AttendanceHomeContract.Event.OnClickNumber(number)
            )
        },
        onEnterClick = {
            viewModel.setEvent(
                AttendanceHomeContract.Event.OnClickOK(state.phoneNumber)
            )
        }
    )
}

@Composable
private fun AttendanceHomeContent(
    modifier: Modifier = Modifier,
    phoneNumber: String,
    onAdminClick: () -> Unit,
    onNumberClick: (String) -> Unit,
    onEnterClick: (String) -> Unit
) {
    Box(
        modifier = modifier.background(DarkGray)
    ) {
        Row {
            IntroSection(
                modifier = Modifier.weight(1f)
            )

            PhoneInputSection(
                modifier = Modifier.weight(1f),
                phoneNumber = phoneNumber,
                onNumberClick = onNumberClick,
                onEnterClick = onEnterClick
            )
        }

        AdminButton(
            modifier = Modifier.align(Alignment.TopStart),
            onAdminClick = onAdminClick
        )
    }
}


@Preview(device = Devices.TABLET, uiMode = Configuration.ORIENTATION_LANDSCAPE)
@Composable
fun AttendanceHomeScreenPreview() {
    AttendanceHomeContent(
        phoneNumber = "010-1234-5678",
        onAdminClick = {},
        onNumberClick = {},
        onEnterClick = {}
    )
}
