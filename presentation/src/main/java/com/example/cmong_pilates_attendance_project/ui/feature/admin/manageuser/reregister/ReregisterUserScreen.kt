package com.example.cmong_pilates_attendance_project.ui.feature.admin.manageuser.reregister

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.ui.component.ConfirmBox
import com.example.cmong_pilates_attendance_project.ui.component.DatePickerDialog
import com.example.cmong_pilates_attendance_project.ui.component.DurationSettingBox
import com.example.cmong_pilates_attendance_project.ui.component.Toolbar
import com.example.cmong_pilates_attendance_project.ui.component.inputText
import com.example.cmong_pilates_attendance_project.utils.toDateString
import com.example.cmong_pilates_attendance_project.utils.showToast
import com.example.cmong_pilates_attendance_project.utils.toDateTriple
import com.example.cmong_pilates_attendance_project.ui.feature.admin.manageuser.UserViewModel


@Composable
fun ReregisterUserScreen(
    navController: NavHostController,
    viewModel: ReregisterUserViewModel,
    userViewModel: UserViewModel,
    context : Context
) {
    val state = viewModel.uiState.collectAsState().value
    val effectFlow = viewModel.effect

    LaunchedEffect(Unit) {
        val user = userViewModel.searchedUser
        val startDateTime = user?.startDateTime?.toDateString()
        val (year, month, day) = startDateTime?.toDateTriple() ?: Triple(0, 0, 0)

        viewModel.setState {
            this.copy(
                no = user?.no ?: 0,
                name = user?.name ?: "null",
                phone = user?.phoneNumber ?: "null",
                durationState = user?.duration ?: "null",
                durationText = user?.duration ?: "null",
                startYear = year,
                startMonth = month,
                startDay = day,
                startDateText = startDateTime ?: "null"
            )
        }
    }

    LaunchedEffect(effectFlow) {
        effectFlow.collect { effect ->
            when (effect) {
                is ReregisterUserContract.Effect.ShowToast -> {
                    context.showToast(effect.msg)
                }

                is ReregisterUserContract.Effect.CompleteRegister -> {
                    userViewModel.updateSearchedUserUsingDate(
                        effect.user.startDateTime,
                        effect.user.endDateTime,
                        effect.user.duration
                    )
                    navController.popBackStack()
                }
            }
        }
    }

    ReregisterUserContent(
        navController = navController,
        viewModel = viewModel,
        state = state
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReregisterUserContent(
    navController: NavHostController,
    viewModel: ReregisterUserViewModel,
    state: ReregisterUserContract.State
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color(0xFF2b2b2b),
        topBar = {
            Toolbar(
                navController,
                stringResource(id = R.string.text_menu_re_register_user)
            )
        },
        content = {
            //회원 정보 입력 뷰
            Box(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(it)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.TopCenter)
                ) {
                    Divider(
                        color = Color(0xFF333333),
                        thickness = 1.dp,
                        modifier = Modifier.padding(bottom = 15.dp)
                    )
                    inputText(
                        titleText = stringResource(
                            id = R.string.text_input_duration
                        ),
                        imageVector = Icons.Filled.List,
                        contentText = state.durationText,
                        handleOnClick = {
                            viewModel.setEvent(ReregisterUserContract.Event.OnClickSetDuration)
                        }
                    )
                    inputText(
                        titleText = stringResource(
                            id = R.string.text_input_start_date
                        ),
                        imageVector = Icons.Filled.DateRange,
                        contentText = state.startDateText,
                        handleOnClick = {
                            viewModel.setEvent(ReregisterUserContract.Event.OnClickSetBeginDate)
                        }
                    )

                    //저장
                    ConfirmBox(
                        text = stringResource(R.string.text_save_button),
                        onClick = {
                            viewModel.setEvent(
                                ReregisterUserContract.Event.ClickedSave(
                                    state.no ?: 0,
                                    state.name ?: "null",
                                    state.phone ?: "null",
                                    state.durationState,
                                    state.startDateText
                                )
                            )
                        }
                    )
                }
            }
        }
    )

    DurationSettingBox(
        isShow = state.isShowDurationView,
        onClickDurationEvent = { duration ->
            viewModel.setEvent(ReregisterUserContract.Event.OnCompleteSetDuration(duration))
        },
        onDismissEvent = { viewModel.setEvent(ReregisterUserContract.Event.OnDismissDurationView) },
        onValueChange = { duration ->
            viewModel.setEvent(ReregisterUserContract.Event.OnChangedDuration(duration))
        },
        durationState = state.durationState,
        durationList = state.durationValues
    )

    DatePickerDialog(
        isShow = state.isShowStartDateView,
        startYear = state.startYear,
        startMonth = state.startMonth,
        startDay = state.startDay,
        onDataSetEvent = { year, month, day ->
            viewModel.setEvent(
                ReregisterUserContract.Event.OnCompleteStartDate(year, month, day)
            )
        }, onDismissEvent = {
            viewModel.setEvent(
                ReregisterUserContract.Event.OnDismissStartDate
            )
        })
}