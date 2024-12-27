package com.example.cmong_pilates_attendance_project.ui.feature.admin.register

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.ui.component.ConfirmBox
import com.example.cmong_pilates_attendance_project.ui.component.DatePickerDialog
import com.example.cmong_pilates_attendance_project.ui.component.DurationSettingBox
import com.example.cmong_pilates_attendance_project.ui.component.Toolbar
import com.example.cmong_pilates_attendance_project.ui.component.inputTextField
import com.example.cmong_pilates_attendance_project.ui.component.inputText
import com.example.cmong_pilates_attendance_project.utils.showToast

@Composable
fun RegisterUserScreen(
    navController: NavHostController,
    viewModel: RegisterUserViewModel,
    context : Context
) {
    val state = viewModel.uiState.collectAsState().value
    val effectFlow = viewModel.effect

    LaunchedEffect(effectFlow) {
        effectFlow.collect { effect ->
            when (effect) {
                is RegisterUserContract.Effect.ShowToast -> {
                    context.showToast(effect.msg)
                }

                is RegisterUserContract.Effect.GoBeforeFragment -> {
                    navController.popBackStack()
                }
            }
        }
    }

    RegisterUserContent(
        viewModel = viewModel,
        navController = navController,
        state = state
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterUserContent(
    viewModel: RegisterUserViewModel,
    navController: NavHostController,
    state: RegisterUserContract.State
) {
    Scaffold(
        modifier = Modifier
            .testTag("REGISTER_USER_SCREEN")
            .fillMaxSize(),
        containerColor = Color(0xFF2b2b2b),
        topBar = { Toolbar(navController, stringResource(R.string.text_menu_register_user)) },
        content = {
            //회원 정보 입력 뷰
            Box(modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(it)
                .fillMaxWidth()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                ) { //hideKeyboard()
                }) {
                Column(
                    modifier = Modifier.align(Alignment.TopCenter)
                ) {
                    Divider(
                        color = Color(0xFF333333),
                        thickness = 1.dp,
                        modifier = Modifier.padding(bottom = 15.dp)
                    )

                    inputTextField(
                        titleText = stringResource(R.string.text_input_name),
                        hintText = stringResource(R.string.text_input_hint_name),
                        keyboardType = KeyboardType.Text,
                        content = state.name,
                        onValueChange = { text ->
                            viewModel.setEvent(RegisterUserContract.Event.OnChangedName(text))
                        },
                        enabled = !state.isShowDurationView
                    )
                    inputTextField(
                        titleText = stringResource(R.string.text_input_phone_number),
                        hintText = stringResource(R.string.text_input_hint_phone_number),
                        keyboardType = KeyboardType.Number,
                        content = state.phone,
                        onValueChange = { text ->
                            viewModel.setEvent(RegisterUserContract.Event.OnChangePhoneNumber(text))
                        },
                        enabled = !state.isShowDurationView
                    )
                    inputText(titleText = stringResource(
                        id = R.string.text_input_duration
                    ),
                        imageVector = Icons.Filled.List,
                        contentText = state.durationText,
                        handleOnClick = {
                            viewModel.setEvent(RegisterUserContract.Event.OnClickSetDuration)
                        })
                    inputText(titleText = stringResource(
                        id = R.string.text_input_start_date
                    ),
                        imageVector = Icons.Filled.DateRange,
                        contentText = state.startDateText,
                        handleOnClick = {
                            viewModel.setEvent(RegisterUserContract.Event.OnClickSetBeginDate)
                        })


                    //저장
                    ConfirmBox(
                        text = stringResource(R.string.text_save_button),
                        onClick = {
                            viewModel.setEvent(
                                RegisterUserContract.Event.ClickedSave(
                                    state.name,
                                    state.phone,
                                    state.durationText,
                                    state.startDateText
                                )
                            )
                        }
                    )
                }
            }
        })

    DatePickerDialog(
        isShow = state.isShowStartDateView,
        startYear = state.startYear,
        startMonth = state.startMonth,
        startDay = state.startDay,
        onDataSetEvent = { year, month, day ->
            viewModel.setEvent(
                RegisterUserContract.Event.OnCompleteStartDate(year, month, day)
            )
        }, onDismissEvent = {
            viewModel.setEvent(
                RegisterUserContract.Event.OnDismissStartDate
            )
        })

    DurationSettingBox(
        isShow = state.isShowDurationView,
        onClickDurationEvent = { duration ->
            viewModel.setEvent(RegisterUserContract.Event.OnCompleteSetDuration(duration))
        },
        onDismissEvent = {
            viewModel.setEvent(RegisterUserContract.Event.OnDismissDurationView)
        },
        onValueChange = { duration ->
            viewModel.setEvent(RegisterUserContract.Event.OnChangeDuration(duration))
        },
        durationState = state.durationState,
        durationList = state.durationValues
    )
}