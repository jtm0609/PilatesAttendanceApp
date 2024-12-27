package com.example.cmong_pilates_attendance_project.ui.feature.admin.manageuser.search

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.ui.component.ConfirmBox
import com.example.cmong_pilates_attendance_project.ui.navigation.AppScreen
import com.example.cmong_pilates_attendance_project.ui.component.Toolbar
import com.example.cmong_pilates_attendance_project.utils.showToast
import com.example.cmong_pilates_attendance_project.ui.feature.admin.manageuser.UserViewModel


@Composable
fun SearchUserScreen(
    navController: NavHostController,
    viewModel: SearchUserViewModel,
    userViewModel: UserViewModel,
    context : Context,
    hideKeyboard: () -> Unit
) {
    val state = viewModel.uiState.collectAsState().value
    val effectFlow = viewModel.effect

    LaunchedEffect(effectFlow) {
        effectFlow.collect { effect ->
            when (effect) {
                is SearchUserContract.Effect.CompleteSearch -> {
                    userViewModel.setSearchedUser(effect.user)
                    navController.navigate(AppScreen.ManageUser.name)
                }

                is SearchUserContract.Effect.ShowToast -> {
                    context.showToast(effect.msg)
                }
            }
        }
    }

    InputPhoneNumberContent(
        navController = navController,
        viewModel = viewModel,
        hideKeyboard = hideKeyboard,
        state = state
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputPhoneNumberContent(
    navController: NavHostController,
    viewModel: SearchUserViewModel,
    hideKeyboard: () -> Unit,
    state: SearchUserContract.State
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF2b2b2b),
        topBar = { Toolbar(navController = navController, titleText = "") },
        content = {
            Box(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
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
                        modifier = Modifier.padding(start = 70.dp, bottom = 20.dp)
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
                        value = state.phone,
                        onValueChange = { textValue ->
                            viewModel.setEvent(
                                SearchUserContract.Event.OnChangePhoneNumber(
                                    textValue
                                )
                            )
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

                    //다음 버튼
                    ConfirmBox(
                        text = stringResource(R.string.text_next_button),
                        onClick = {
                            viewModel.setEvent(
                                SearchUserContract.Event.OnClickNextButton(
                                    state.phone
                                )
                            )
                        }
                    )
                }
            }
        }
    )
}