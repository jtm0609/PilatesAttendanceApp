package com.example.cmong_pilates_attendance_project.ui.admin.screen

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.fragment.findNavController
import com.chargemap.compose.numberpicker.ListItemPicker
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.utils.Constant
import com.example.cmong_pilates_attendance_project.utils.Utils

@Composable
fun inputTextItem(inputType: Int) {
    var titleText: String? = null
    var imageVector: ImageVector? = null
    var contentText: String? = null
    when (inputType) {
        Constant.INPUT_DURATION -> {
            titleText = stringResource(
                id = R.string.text_input_duration
            )
            imageVector = Icons.Filled.List
            contentText = viewModel.durationText
        }

        Constant.INPUT_START_DATE -> {
            titleText = stringResource(
                id = R.string.text_input_start_date
            )
            imageVector = Icons.Filled.DateRange
            contentText = viewModel.startDateText
        }
    }

    Text(
        text = titleText!!,
        color = Color.White,
        fontSize = 30.sp,
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(top = 20.dp, start = 70.dp, bottom = 10.dp)
    )
    Box(
        modifier = Modifier
            .padding(start = 70.dp, end=70.dp)
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(0xFFE8E0ED))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                if (!viewModel.durationVisibility) {
                    iconClick(inputType)
                }
            }
    ) {
        Text(
            text = contentText!!,
            color = Color.Black,
            fontSize = 15.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 12.dp)
        )

        IconButton(
            onClick = {
                if (!viewModel.durationVisibility) {
                    iconClick(inputType)
                }
            },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector!!, "backIcon", tint = Color.Black
            )
        }
    }
}

//이용 기간 설정 뷰
@Composable
fun durationSettingView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2b2b2b)),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "이용 기간을 선택해주세요.",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 25.dp)
            )
            Divider(
                color = Color(0xFF333333),
                thickness = 1.dp,
                modifier = Modifier.padding(top = 25.dp)
            )
        }

        ListItemPicker(
            label = { it },
            value = viewModel.durationState,
            onValueChange = { viewModel.setDurationState(it) },
            list = viewModel.durationValues,
            modifier = Modifier
                .align(Alignment.Center)
                .width(300.dp),
            textStyle = TextStyle(fontSize = 25.sp, color = Color.White),
            dividersColor = Color(0xFFE8E0ED)
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp, start = 60.dp, end = 60.dp)
                .clickable {
                    clickDurationSettingButton(viewModel.durationState)
                },
        ) {
            Text(
                text = stringResource(R.string.text_setting_button),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReregisterUserScreen() {
    Scaffold(
        containerColor = Color(0xFF2b2b2b),
        topBar = { toolbar() },
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
                    inputTextItem(inputType = Constant.INPUT_DURATION)
                    inputTextItem(inputType = Constant.INPUT_START_DATE)
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

    //이용 기간 설정 뷰
    if (viewModel.durationVisibility) {
        durationSettingView()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun toolbar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.text_menu_re_register_user),
                color = Color.White,
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF2b2b2b)),
        navigationIcon = {
            IconButton(onClick = {
                if (!viewModel.durationVisibility)
                    findNavController().popBackStack()
            }) {
                Icon(
                    Icons.Filled.ArrowBack, "backIcon", tint = Color.White,
                    modifier = Modifier.size(50.dp).padding(end = 10.dp)
                )
            }
        },
    )
}




//저장 버튼 클릭
@RequiresApi(Build.VERSION_CODES.O)
private fun clickSaveButton() {
    val userStartDate = viewModel.startDateText
    val userDuration = viewModel.durationState
    val userStartDateTime = Utils.dateStringToTimestamp(userStartDate)
    val userEndDateTime = Utils.getEndDateTimeMilli(userStartDateTime, userDuration)
    userViewModel.updateSearchedUserUsingDate(userStartDateTime, userEndDateTime, userDuration)
    viewModel.reRegisterUser(userViewModel.searchedUser.value!!)
}

//이용 기간 설정 클릭
private fun clickDurationSettingButton(duration: String) {
    viewModel.setVisibilityDuration(false)
    viewModel.setDurationText(duration)
}

//아이콘 클릭 이벤트
private fun iconClick(iconType: Int) {
    if (iconType == Constant.INPUT_DURATION) {
        viewModel.setVisibilityDuration(true)
    } else {
        showDateDialog()
    }
}

private fun showDateDialog() {

    val dlg = DatePickerDialog(
        mContext,
        { pView, pYear, pMonth, pDayOfMonth ->
            viewModel.setStartDate(pYear, pMonth, pDayOfMonth)
            viewModel.setStartDateText(
                getString(
                    R.string.text_start_date,
                    pYear,
                    pMonth + 1,
                    pDayOfMonth
                )
            )
        },
        viewModel.startYear, viewModel.startMonth, viewModel.startDay
    )
    dlg.show()
}