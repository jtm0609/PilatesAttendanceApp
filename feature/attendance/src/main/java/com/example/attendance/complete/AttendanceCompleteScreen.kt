package com.example.attendance.complete

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.designsystem.theme.DarkGray
import com.example.designsystem.theme.White
import com.example.feature.attendance.R
import kotlinx.coroutines.delay

@Composable
fun AttendanceCompleteScreen(
    navController: NavHostController,
    viewModel: AttendanceCompleteViewModel
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    // 3초 후 자동으로 이전 화면으로 돌아가기
    LaunchedEffect(Unit) {
        delay(3000L)
        navController.popBackStack()
    }

    AttendanceCompleteContent(
        state = state
    )
}

@Composable
fun AttendanceCompleteContent(
    state: AttendanceCompleteContract.State
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGray)
    ) {
        // 중앙 콘텐츠 - 출석 완료 및 마일리지 정보
        CenterContent(
            userName = state.name,
            mileage = state.mileage,
            textColor = White,
            borderColor = White
        )

        // 로고
        AppLogo(modifier = Modifier.align(Alignment.BottomEnd))
    }
}

@Composable
private fun CenterContent(
    userName: String,
    mileage: Int,
    textColor: Color,
    borderColor: Color
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        
        // 사용자 이름과 출석 완료 메시지
        UserGreeting(
            userName = userName,
            textColor = textColor
        )
        
        Spacer(modifier = Modifier.height(40.dp))
        
        // 마일리지 추가 메시지
        MessageText(
            textRes = R.string.msg_add_mileage,
            textColor = textColor
        )
        
        Spacer(modifier = Modifier.height(40.dp))
        
        // 현재 마일리지 정보
        MileageDisplay(
            mileage = mileage,
            textColor = textColor,
            borderColor = borderColor
        )
        
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun UserGreeting(
    userName: String,
    textColor: Color
) {
    Text(
        text = "\"$userName\"님",
        color = textColor,
        style = MaterialTheme.typography.displayMedium,
        textAlign = TextAlign.Center
    )
    
    MessageText(
        textRes = R.string.msg_complete_attendance,
        textColor = textColor
    )
}

@Composable
private fun MessageText(
    textRes: Int,
    textColor: Color
) {
    Text(
        text = stringResource(textRes),
        color = textColor,
        style = MaterialTheme.typography.displayMedium,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun MileageDisplay(
    mileage: Int,
    textColor: Color,
    borderColor: Color
) {
    MessageText(
        textRes = R.string.msg_current_mileage,
        textColor = textColor
    )
    
    Spacer(modifier = Modifier.height(10.dp))
    
    Box(
        modifier = Modifier
            .border(1.dp, borderColor)
            .padding(15.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = mileage.toString(),
            color = textColor,
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun AppLogo(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(200.dp)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "image",
            contentScale = ContentScale.Fit
        )
    }
}