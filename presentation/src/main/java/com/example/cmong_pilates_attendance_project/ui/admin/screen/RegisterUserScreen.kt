package com.example.cmong_pilates_attendance_project.ui.admin.screen

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.ui.component.DurationSettingBox
import com.example.cmong_pilates_attendance_project.ui.component.Toolbar
import com.example.cmong_pilates_attendance_project.ui.component.inputEditItem
import com.example.cmong_pilates_attendance_project.ui.component.inputTextItem
import com.example.cmong_pilates_attendance_project.utils.Utils
import com.example.cmong_pilates_attendance_project.utils.showToast
import com.example.cmong_pilates_attendance_project.viewmodel.RegisterUserViewModel
import com.example.data.model.UserEntity


@Composable
fun RegisterUserScreen(
    navController: NavHostController,
    viewModel: RegisterUserViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val showDateDialog: () -> Unit = {
        val dlg = DatePickerDialog(
            context, { pView, pYear, pMonth, pDayOfMonth ->
                viewModel.setStartDate(
                    pYear, pMonth, pDayOfMonth
                )
                viewModel.setStartDateText(
                    context.getString(
                        R.string.text_start_date, pYear, pMonth + 1, pDayOfMonth
                    )
                )
            }, viewModel.startYear, viewModel.startMonth, viewModel.startDay
        )
        dlg.show()
    }

    //이용 기간 설정 클릭
    val handleClickDuration: (String) -> Unit = { duration ->
        viewModel.setVisibilityDuration(false)
        viewModel.setDurationText(duration)
    }
    val handlePickerOnValueChange: (String) -> Unit = { duration ->
        viewModel.setDurationState(duration)
    }

    val clickSaveButton: () -> Unit = {
        val userStartDate = viewModel.startDateText
        val userDuration = viewModel.durationText
        val userName = viewModel.name
        val userPhone = viewModel.phone

        if (userName.isBlank() || userPhone.isBlank() || userDuration.isBlank() || userStartDate.isBlank()) {
            context.showToast(R.string.msg_text_empty)
        }

        if (!Utils.isValidPhoneNumber(userPhone)) {
            context.showToast(R.string.msg_text_not_valid_phone_number)
        }

        val userStartDateTime = Utils.dateStringToTimestamp(userStartDate)
        val userEndDateTime = Utils.getEndDateTimeMilli(userStartDateTime, userDuration)

        val user = UserEntity(
            name = userName,
            phoneNumber = userPhone,
            duration = userDuration,
            startDateTime = userStartDateTime,
            endDateTime = userEndDateTime
        )
        viewModel.addUser(user)
    }

    //observe
    if (viewModel.isSuccessAddUser) {
        context.showToast(msg = stringResource(R.string.text_complete_add_user))
        navController.popBackStack()
    } else {
        context.showToast(msg = stringResource(R.string.text_duplicate_add_user))
    }

    RegisterUserContent(
        viewModel = viewModel,
        navController = navController,
        showDateDialog = showDateDialog,
        clickSaveButton = clickSaveButton
    )

    //이용 기간 설정 뷰
    if (viewModel.durationVisibility) {
        DurationSettingBox(
            handleClickDuration = handleClickDuration,
            handlePickerOnValueChange = handlePickerOnValueChange,
            durationState = viewModel.durationState,
            durationList = viewModel.durationValues
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterUserContent(
    viewModel: RegisterUserViewModel,
    navController: NavHostController,
    showDateDialog: () -> Unit,
    clickSaveButton: () -> Unit
) {
    Scaffold(modifier = Modifier.testTag("REGISTER_USER_SCREEN"),
        containerColor = Color(0xFF2b2b2b),
        topBar = { Toolbar(navController, stringResource(R.string.text_menu_register_user)) },
        content = {
            //회원 정보 입력 뷰
            Box(modifier = Modifier
                .padding(it)
                .fillMaxSize()
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

                    inputEditItem(
                        titleText = stringResource(R.string.text_input_name),
                        hintText = stringResource(R.string.text_input_hint_name),
                        keyboardType = KeyboardType.Text,
                        content = viewModel.name,
                        onValueChange = { text ->
                            if (text.length <= 11) {
                                viewModel.setNameText(text)
                            }
                        },
                        enabled = !viewModel.durationVisibility
                    )
                    inputEditItem(
                        titleText = stringResource(R.string.text_input_phone_number),
                        hintText = stringResource(R.string.text_input_hint_phone_number),
                        keyboardType = KeyboardType.Number,
                        content = viewModel.phone,
                        onValueChange = { text ->
                            if (text.length <= 11 && !text.contains(",") && !text.contains("-")) {
                                viewModel.setPhoneText(text)
                            }
                        },
                        enabled = !viewModel.durationVisibility
                    )
                    inputTextItem(titleText = stringResource(
                        id = R.string.text_input_duration
                    ),
                        imageVector = Icons.Filled.List,
                        contentText = viewModel.durationText,
                        handleOnClick = {
                            if (!viewModel.durationVisibility) {
                                viewModel.setVisibilityDuration(true)
                            }
                        })
                    inputTextItem(titleText = stringResource(
                        id = R.string.text_input_start_date
                    ),
                        imageVector = Icons.Filled.DateRange,
                        contentText = viewModel.startDateText,
                        handleOnClick = {
                            if (!viewModel.durationVisibility) {
                                showDateDialog()
                            }
                        })
                }

                //저장 버튼
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 30.dp, start = 60.dp, end = 60.dp)
                        .testTag("SAVE_BOX")
                        .clickable(
                            onClick = clickSaveButton
                        ),
                ) {
                    Text(
                        text = stringResource(R.string.text_save_button),
                        color = Color.White,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(
                                Color(0xFF333333), shape = RoundedCornerShape(12.dp)
                            )
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
            }
        })
}