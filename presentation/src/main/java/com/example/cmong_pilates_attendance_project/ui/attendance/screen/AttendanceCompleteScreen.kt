package com.example.cmong_pilates_attendance_project.ui.attendance.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.viewmodel.AttendanceViewModel
import kotlinx.coroutines.delay

@Composable
fun AttendanceCompleteScreen(
    navController: NavHostController,
    attendanceViewModel: AttendanceViewModel
){

    LaunchedEffect(Unit) {
        delay(3000)
        navController.popBackStack()
    }

    AttendanceCompleteContent(viewModel = attendanceViewModel)
}

@Composable
fun AttendanceCompleteContent(
    viewModel: AttendanceViewModel = hiltViewModel()
){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF2b2b2b))) {
        Column(
            modifier= Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "\"${viewModel.searchedUser.value?.name}\"님",
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
                    .padding(15.dp)) {
                Text(
                    text = viewModel.searchedUser.value?.mileage.toString(),
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
                painter= painterResource(id = R.drawable.app_logo),
                contentDescription = "image",
                contentScale = ContentScale.Crop
            )
        }

    }
}