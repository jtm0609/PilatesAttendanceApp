package com.example.cmong_pilates_attendance_project.ui.feature.attendance.complete

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cmong_pilates_attendance_project.R
import kotlinx.coroutines.delay

@Composable
fun AttendanceCompleteScreen(
    navController: NavHostController,
    viewModel: AttendanceCompleteViewModel
) {
    val state = viewModel.uiState.collectAsState().value

    LaunchedEffect(Unit) {
        delay(3000)
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
            .background(Color(0xFF2b2b2b))
            .verticalScroll(rememberScrollState())
            .horizontalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "\"${state.name}\"님",
                color = Color.White,
                fontSize = 55.sp,
                textAlign = TextAlign.Center

            )
            Text(
                text = stringResource(R.string.msg_complete_attendance),
                color = Color.White,
                fontSize = 55.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(R.string.msg_add_mileage),
                color = Color.White,
                fontSize = 55.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(R.string.msg_current_mileage),
                color = Color.White,
                fontSize = 55.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                Modifier
                    .border(1.dp, Color.White)
                    .padding(15.dp)
            ) {
                Text(
                    text = state.mileage.toString(),
                    color = Color.White,
                    fontSize = 55.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        Box(
            modifier = Modifier
                .height(200.dp)
                .width(200.dp)
                .align(Alignment.BottomEnd)
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "image",
                contentScale = ContentScale.Crop
            )
        }
    }
}