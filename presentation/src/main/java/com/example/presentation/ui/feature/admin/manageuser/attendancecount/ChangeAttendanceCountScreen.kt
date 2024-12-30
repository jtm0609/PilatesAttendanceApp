package com.example.presentation.ui.feature.admin.manageuser.attendancecount

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.presentation.ui.component.ConfirmBox
import com.example.presentation.ui.component.Toolbar
import com.example.presentation.utils.showToast
import com.example.presentation.R


@Composable
fun ChangeAttendanceCountScreen(
    navController: NavHostController,
    viewModel: ChangeAttendanceCountViewModel,
    context : Context
) {
    val state = viewModel.uiState.collectAsState().value
    val effectFlow = viewModel.effect

    LaunchedEffect(effectFlow) {
        effectFlow.collect { effect->
            when (effect) {
                is ChangeAttendanceCountContract.Effect.CompleteChangeMileage -> {
                    navController.popBackStack()
                }

                is ChangeAttendanceCountContract.Effect.ShowToast -> {
                    context.showToast(effect.msg)
                }
            }
        }
    }

    ChangeAttendanceCountContent(
        navController = navController,
        viewModel = viewModel,
        state = state
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeAttendanceCountContent(
    navController: NavHostController,
    viewModel: ChangeAttendanceCountViewModel,
    state: ChangeAttendanceCountContract.State
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF2b2b2b),
        topBar = {
            Toolbar(
                navController = navController,
                titleText = stringResource(id = R.string.text_menu_change_attendance_count)
            )
        },
        content = {
            Divider(
                color = Color(0xFF333333),
                thickness = 1.dp,
                modifier = Modifier
                    .padding(it)
            )

            Box(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(it)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .background(
                                Color(0xFF333333),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(60.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.text_max_attendance_count),
                            color = Color.White,
                            fontSize = 40.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = stringResource(
                                    R.string.text_attendance_count,
                                    state.attendanceCount
                                ),
                                color = Color.White,
                                fontSize = 80.sp,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = "↑",
                                color = Color.White,
                                fontSize = 110.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.clickable {
                                    viewModel.setEvent(ChangeAttendanceCountContract.Event.OnClickCountUp)
                                }
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "↓",
                                color = Color.White,
                                fontSize = 110.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.clickable {
                                    viewModel.setEvent(ChangeAttendanceCountContract.Event.OnClickCountDown)
                                }
                            )
                        }
                        Text(
                            text = "-",
                            color = Color.White,
                            fontSize = 60.sp,
                            textAlign = TextAlign.Center
                        )
                    }

                    //저장
                    ConfirmBox(
                        text = stringResource(R.string.text_save_button),
                        onClick = {
                            viewModel.setEvent(
                                ChangeAttendanceCountContract.Event.OnClickSaveButton(
                                    state.attendanceCount
                                )
                            )
                        }
                    )
                }
            }
        }
    )
}