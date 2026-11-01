package com.example.attendance.complete

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.attendance.complete.component.AutoReturnBar
import com.example.attendance.complete.component.CheckMark
import com.example.attendance.complete.component.MemberName
import com.example.attendance.complete.component.MileageSummary
import com.example.attendance.common.AppLogo
import com.example.designsystem.theme.PilatesTheme
import com.example.designsystem.theme.Typography
import com.example.feature.attendance.R
import kotlinx.coroutines.delay

private const val AUTO_RETURN_MS = 3000

@Composable
fun AttendanceCompleteScreen(
    navController: NavHostController,
    viewModel: AttendanceCompleteViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        delay(AUTO_RETURN_MS.toLong())
        navController.popBackStack()
    }

    AttendanceCompleteContent(state = state)
}

@Composable
private fun AttendanceCompleteContent(
    state: AttendanceCompleteContract.State
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PilatesTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            CheckMark()

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = stringResource(R.string.msg_complete_attendance),
                color = PilatesTheme.colors.textSecondary,
                style = Typography.titleMediumM,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            MemberName(name = state.name)

            Spacer(modifier = Modifier.height(40.dp))

            MileageSummary(mileage = state.mileage)

            Spacer(modifier = Modifier.weight(1f))
        }

        AppLogo(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 24.dp, bottom = 16.dp)
                .width(160.dp)
                .height(100.dp)
        )

        AutoReturnBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            durationMillis = AUTO_RETURN_MS
        )
    }
}

@Preview(device = Devices.TABLET)
@Composable
private fun AttendanceCompletePreview() {
    PilatesTheme {
        AttendanceCompleteContent(
            state = AttendanceCompleteContract.State(name = "김하늘", mileage = 12)
        )
    }
}
