package com.example.cmong_pilates_attendance_project.ui.attendance.screen

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.findNavController
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.ui.PilatesAppScreen
import com.example.cmong_pilates_attendance_project.ui.admin.activity.AdminActivity
import com.example.cmong_pilates_attendance_project.utils.showToast
import com.example.cmong_pilates_attendance_project.viewmodel.AdminViewModel
import com.example.cmong_pilates_attendance_project.viewmodel.AttendanceViewModel

@Composable
fun AttendanceMainScreen(
    navController: NavHostController,
    viewModel: AttendanceViewModel,
    adminViewModel: AdminViewModel,
) {

    if (adminViewModel.adminData != null) {
        viewModel.setMaxAttendanceCount(adminViewModel.adminData?.maxAttendance ?: 0)
    }
    if (viewModel.isAlreadyAttendance) {
        LocalContext.current.showToast(R.string.text_noti_already_attendance_user)
    }
    if (viewModel.isNoExistUser) {
        LocalContext.current.showToast(R.string.text_noti_retry_phone_number)
    }
    if (viewModel.isSuccessAttendance) {
        navController.navigate(PilatesAppScreen.AttendanceComplete.name)
    }

    val onClickNumber: (String) -> Unit = { number ->
        viewModel.apply {
            if (phoneNumber.length < 8) {
                setPhoneNumber(phoneNumber + number)
            }
        }
    }
    val onClickDelete: () -> Unit = {
        with(viewModel){
            setPhoneNumber(phoneNumber.dropLast(1))
        }
    }
    val onClickOk: () -> Unit = {
        with(viewModel){
            checkUser("010$phoneNumber")
        }
    }
    val movePage: () -> Unit = {
        navController.navigate(PilatesAppScreen.AdminMenu.name)
    }

    AttendanceMainContent(
        viewModel = viewModel,
        onClickNumber = onClickNumber,
        onClickDelete = onClickDelete,
        onClickOk = onClickOk,
        movePage = movePage
    )
}

@Composable
fun AttendanceMainContent(
    viewModel : AttendanceViewModel,
    onClickNumber:(String) -> Unit,
    onClickDelete:() -> Unit,
    onClickOk:() -> Unit,
    movePage:() -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .clipToBounds()
            .testTag("MAIN_VIEW")
            .background(Color(0xFF2b2b2b)),
    ) {
        IconButton(
            onClick = { movePage() }, modifier = Modifier.padding(start = 20.dp, top = 30.dp)

        ) {
            Icon(
                Icons.Filled.Person, "backIcon", tint = Color.White, modifier = Modifier.size(45.dp)
            )
        }
        Column(
            Modifier
                .fillMaxHeight()
                .weight(1f)
                .clipToBounds()
                .padding(top = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .height(400.dp)
                    .width(450.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = stringResource(R.string.msg_info_pilates),
                color = Color.White,
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 10.dp)
            )
        }

        Column(
            Modifier
                .fillMaxHeight()
                .weight(1f)
                .clipToBounds()
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.msg_input_phone_number),
                color = Color.White,
                fontSize = 30.sp,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .background(Color.White)
                    .width(500.dp)
                    .height(50.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = viewModel.phoneNumber,
                    color = Color.Black,
                    letterSpacing = 1.5.sp,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    modifier = Modifier.testTag("PHONE_NUMBER_TEXT")
                )


            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .border(2.dp, Color.White)
                    .padding(10.dp)
                    .width(400.dp)
            ) {
                Row() {
                    Text(text = "7",
                        color = Color.White,
                        fontSize = 90.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(120.dp)
                            .height(110.dp)
                            .wrapContentSize(align = Alignment.Center)
                            .testTag("7")
                            .clickable { onClickNumber("7") })
                    Text(text = "8",
                        color = Color.White,
                        fontSize = 90.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(120.dp)
                            .height(110.dp)
                            .wrapContentSize(align = Alignment.Center)
                            .testTag("8")
                            .clickable { onClickNumber("8") })
                    Text(text = "9",
                        color = Color.White,
                        fontSize = 90.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(120.dp)
                            .height(110.dp)
                            .wrapContentSize(align = Alignment.Center)
                            .testTag("9")
                            .clickable { onClickNumber("9") })
                }
                Row() {
                    Text(text = "4",
                        color = Color.White,
                        fontSize = 90.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(120.dp)
                            .height(110.dp)
                            .wrapContentSize(align = Alignment.Center)
                            .testTag("4")
                            .clickable { onClickNumber("4") })
                    Text(text = "5",
                        color = Color.White,
                        fontSize = 90.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(120.dp)
                            .height(110.dp)
                            .wrapContentSize(align = Alignment.Center)
                            .testTag("5")
                            .clickable { onClickNumber("5") })
                    Text(text = "6",
                        color = Color.White,
                        fontSize = 90.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(120.dp)
                            .height(110.dp)
                            .wrapContentSize(align = Alignment.Center)
                            .testTag("6")
                            .clickable { onClickNumber("6") })
                }
                Row() {
                    Text(text = "1",
                        color = Color.White,
                        fontSize = 90.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(120.dp)
                            .height(110.dp)
                            .wrapContentSize(align = Alignment.Center)
                            .testTag("1")
                            .clickable { onClickNumber("1") })
                    Text(text = "2",
                        color = Color.White,
                        fontSize = 90.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(120.dp)
                            .height(110.dp)
                            .wrapContentSize(align = Alignment.Center)
                            .testTag("2")
                            .clickable { onClickNumber("2") })
                    Text(text = "3",
                        color = Color.White,
                        fontSize = 90.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(120.dp)
                            .height(110.dp)
                            .wrapContentSize(align = Alignment.Center)
                            .testTag("3")
                            .clickable { onClickNumber("3") })
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { onClickDelete() },
                        modifier = Modifier
                            .width(120.dp)
                            .height(110.dp)
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            "backIcon",
                            tint = Color.White,
                            modifier = Modifier
                                .width(90.dp)
                                .height(100.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }
                    Text(text = "0",
                        color = Color.White,
                        fontSize = 90.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .wrapContentSize(Alignment.Center)
                            .width(120.dp)
                            .align(Alignment.CenterVertically)
                            .clickable { onClickNumber("0") })

                    Text(text = "입장",
                        color = Color.White,
                        fontSize = 60.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .wrapContentSize(align = Alignment.Center)
                            .width(120.dp)
                            .align(Alignment.CenterVertically)
                            .testTag("ENTRANCE")
                            .clickable { onClickOk() })
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}
