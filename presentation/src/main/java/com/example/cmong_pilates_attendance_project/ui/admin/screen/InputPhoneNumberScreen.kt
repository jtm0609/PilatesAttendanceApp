package com.example.cmong_pilates_attendance_project.ui.admin.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.state.PilatesState
import com.example.cmong_pilates_attendance_project.ui.PilatesAppScreen
import com.example.cmong_pilates_attendance_project.ui.component.Toolbar
import com.example.cmong_pilates_attendance_project.utils.LogUtil
import com.example.cmong_pilates_attendance_project.utils.showToast
import com.example.cmong_pilates_attendance_project.viewmodel.UserViewModel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputPhoneNumberScreen(
    navController: NavHostController,
    userViewModel: UserViewModel
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val clickNextButton: () -> Unit = {
        userViewModel.getUser(userViewModel.userPhoneNumber)
    }

    val hideKeyboard: () -> Unit = {
        keyboardController?.hide()
    }

    val toastMessage = when(userViewModel.state) {
        is PilatesState.Success -> stringResource(R.string.text_noti_input_phone_number)
        else ->{
            if(userViewModel.userPhoneNumber.isBlank()){
                stringResource(R.string.text_noti_input_phone_number)
            }else{
                stringResource(R.string.text_not_exist_user)
            }
        }
    }

    //observe
    LaunchedEffect(userViewModel.state) {
        toastMessage.let {
            context.showToast(it)
        }
        if(userViewModel.state == PilatesState.Success) {
            navController.navigate(PilatesAppScreen.ManageUser.name)
        }
    }

    InputPhoneNumberContent(
        navController = navController,
        userViewModel = userViewModel,
        clickNextButton = clickNextButton,
        hideKeyboard = hideKeyboard
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputPhoneNumberContent(
    navController: NavHostController,
    userViewModel: UserViewModel,
    clickNextButton: () -> Unit,
    hideKeyboard: () -> Unit
) {
    Scaffold(
        containerColor = Color(0xFF2b2b2b),
        topBar = { Toolbar(navController = navController, titleText = "") },
        content = {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                    ) { hideKeyboard() }
            ) {
                Column(
                    modifier = Modifier.align(Alignment.TopCenter)
                ) {
                    Divider(
                        color = Color(0xFF333333),
                        thickness = 1.dp,
                        modifier = Modifier.padding(bottom = 15.dp)
                    )

                    Text(
                        text = stringResource(
                            id = R.string.text_title_input_phone_number
                        ),
                        color = Color.White,
                        fontSize = 40.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(top = 100.dp, start = 70.dp, bottom = 20.dp)
                    )
                    Text(
                        text = stringResource(
                            id = R.string.text_guide_input_phone_number
                        ),
                        color = Color.Gray,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(start = 70.dp)
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        text = "대한민국(Repulbic of Korea)",
                        color = Color.White,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(start = 70.dp, top = 10.dp, bottom = 20.dp)
                    )

                    TextField(
                        value = userViewModel.userPhoneNumber,
                        onValueChange = { textValue ->
                            if (textValue.length <= 11) {
                                userViewModel.setUserPhoneNumber(textValue)
                            }
                        },
                        placeholder = {
                            Text(
                                stringResource(id = R.string.text_title_input_phone_number),
                                color = Color.Gray
                            )
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier
                            .padding(start = 70.dp, bottom = 20.dp, end = 70.dp)
                            .fillMaxWidth()
                            .height(60.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(
                            bottom = 30.dp, start = 60.dp, end = 60.dp,
                            top = 30.dp
                        )
                        .clickable {
                            clickNextButton()
                        },
                ) {
                    Text(
                        text = stringResource(R.string.text_next_button),
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