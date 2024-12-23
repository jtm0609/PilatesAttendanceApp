package com.example.cmong_pilates_attendance_project.ui.admin.screen

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.fragment.findNavController
import com.chargemap.compose.numberpicker.ListItemPicker
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.ui.component.DurationSettingBox
import com.example.cmong_pilates_attendance_project.ui.component.Toolbar
import com.example.cmong_pilates_attendance_project.ui.component.inputTextItem
import com.example.cmong_pilates_attendance_project.utils.Utils
import com.example.cmong_pilates_attendance_project.utils.showToast
import com.example.cmong_pilates_attendance_project.viewmodel.ReregisterUserViewModel
import com.example.cmong_pilates_attendance_project.viewmodel.UserViewModel


@Composable
fun ReregisterUserScreen(
    navController: NavHostController,
    viewModel: ReregisterUserViewModel = hiltViewModel(),
    userViewModel: UserViewModel = viewModel()
) {

    //저장 버튼 클릭
    val clickSaveButton: () -> Unit = {
        val userStartDate = viewModel.startDateText
        val userDuration = viewModel.durationState
        val userStartDateTime = Utils.dateStringToTimestamp(userStartDate)
        val userEndDateTime = Utils.getEndDateTimeMilli(userStartDateTime, userDuration)
        userViewModel.updateSearchedUserUsingDate(userStartDateTime, userEndDateTime, userDuration)
        userViewModel.searchedUser?.let { viewModel.reRegisterUser(it) }
    }

    val context = LocalContext.current

    //이용 기간 설정 클릭
    val handleClickDuration: (String) -> Unit = { duration ->
        viewModel.setVisibilityDuration(false)
        viewModel.setDurationText(duration)
    }
    val handlePickerOnValueChange: (String) -> Unit = { duration ->
        viewModel.setDurationState(duration)
    }

    val showDateDialog: () -> Unit = {
        val dlg = DatePickerDialog(
            context,
            { pView, pYear, pMonth, pDayOfMonth ->
                viewModel.setStartDate(
                    pYear,
                    pMonth,
                    pDayOfMonth
                )
                viewModel.setStartDateText(
                    context.getString(
                        R.string.text_start_date,
                        pYear,
                        pMonth + 1,
                        pDayOfMonth
                    )
                )
            },
            viewModel.startYear,
            viewModel.startMonth,
            viewModel.startDay
        )
        dlg.show()
    }

    //검색된 유저의 startDate를 불러와 현재 UI 데이터에 업데이트한다.
    val updateUserStartDate: (String) -> Unit = { dateStr ->
        val year = dateStr.split("-")[0].toInt()
        val month = dateStr.split("-")[1].toInt() - 1
        val date = dateStr.split("-")[2].toInt()
        viewModel.setStartDate(year, month, date)
    }

    //fun init
    //이용 기간 UI 데이터 업데이트
    val duration = userViewModel.searchedUser?.duration
    viewModel.setDurationState(duration)
    viewModel.setDurationText(duration)

    //이용 시작일 UI 데이터 업데이트
    val userStartDateTime = userViewModel.searchedUser?.startDateTime
    val userStartDateTimeStr = Utils.convertTimeStampToDateString(userStartDateTime)
    updateUserStartDate(userStartDateTimeStr)
    viewModel.setStartDateText(userStartDateTimeStr)


    //observe
    if (viewModel.isSuccessUpdateUser) {
        context.showToast(R.string.text_success_re_register_user)
        navController.popBackStack()
    } else {
        context.showToast(R.string.text_fail_re_register_user)
    }


    //이용 기간 설정 뷰
    if (viewModel.durationVisibility) {
        DurationSettingBox(
            handleClickDuration = handleClickDuration,
            handlePickerOnValueChange = handlePickerOnValueChange,
            durationState = viewModel.durationState,
            durationList = viewModel.durationValues
        )
    }

    ReregisterUserContent(
        navController = navController,
        viewModel = viewModel,
        clickSaveButton = clickSaveButton,
        showDateDialog = showDateDialog
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReregisterUserContent(
    navController: NavHostController,
    viewModel: ReregisterUserViewModel,
    clickSaveButton: () -> Unit,
    showDateDialog: () -> Unit
) {
    Scaffold(
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
                    .padding(it)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.align(Alignment.TopCenter)
                ) {
                    Divider(
                        color = Color(0xFF333333),
                        thickness = 1.dp,
                        modifier = Modifier.padding(bottom = 15.dp)
                    )
                    inputTextItem(
                        titleText = stringResource(
                            id = R.string.text_input_duration
                        ),
                        imageVector = Icons.Filled.List,
                        contentText = viewModel.durationText,
                        handleOnClick = {
                            if (!viewModel.durationVisibility) {
                                viewModel.setVisibilityDuration(true)
                            }
                        }
                    )
                    inputTextItem(
                        titleText = stringResource(
                            id = R.string.text_input_start_date
                        ),
                        imageVector = Icons.Filled.DateRange,
                        contentText = viewModel.startDateText,
                        handleOnClick = {
                            if (!viewModel.durationVisibility) {
                                showDateDialog()
                            }
                        }
                    )
                }

                //저장 버튼
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 30.dp, start = 60.dp, end = 60.dp)
                        .clickable {
                            clickSaveButton()
                        },
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
                                Color(0xFF333333),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
            }
        }
    )
}