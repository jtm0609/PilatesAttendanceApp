package com.example.presentation.ui.feature.admin.manageuser.search

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.designsystem.component.ConfirmBox
import com.example.designsystem.component.Progress
import com.example.designsystem.component.Toolbar
import com.example.presentation.utils.showToast
import com.example.presentation.ui.feature.admin.manageuser.UserViewModel
import com.example.presentation.R
import com.example.presentation.navigation.AppDestination
import com.example.designsystem.theme.DarkGray
import com.example.designsystem.theme.LightGray
import com.example.designsystem.theme.Typography
import com.example.designsystem.theme.White

@Composable
fun SearchUserScreen(
    navController: NavHostController,
    viewModel: SearchUserViewModel,
    userViewModel: UserViewModel,
    context: Context,
    hideKeyboard: () -> Unit
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    val effectFlow = viewModel.effect

    LaunchedEffect(effectFlow) {
        effectFlow.collect { effect ->
            when (effect) {
                is SearchUserContract.Effect.CompleteSearch -> {
                    userViewModel.setSearchedUser(effect.user)
                    navController.navigate(AppDestination.ManageUser)
                }
                is SearchUserContract.Effect.ShowToast -> {
                    context.showToast(effect.msg)
                }
            }
        }
    }

    PhoneNumberInputScreen(
        navController = navController,
        state = state,
        onPhoneChange = { phoneNumber ->
            viewModel.setEvent(SearchUserContract.Event.OnChangePhoneNumber(phoneNumber))
        },
        onNextClick = { phoneNumber ->
            viewModel.setEvent(SearchUserContract.Event.OnClickNextButton(phoneNumber))
        },
        hideKeyboard = hideKeyboard
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PhoneNumberInputScreen(
    navController: NavHostController,
    state: SearchUserContract.State,
    onPhoneChange: (String) -> Unit,
    onNextClick: (String) -> Unit,
    hideKeyboard: () -> Unit
) {
    val standardPadding = 70.dp

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = DarkGray,
        topBar = { Toolbar(navController = navController, titleText = "") },
    ) { paddingValues ->
        if (state.isLoading) {
            Progress()
        }

        Box(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { hideKeyboard() }
        ) {
            Column(
                modifier = Modifier.align(Alignment.TopCenter)
            ) {
                Divider(
                    color = LightGray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(bottom = 15.dp)
                )

                HeaderSection(standardPadding)
                
                CountrySection(standardPadding)

                PhoneNumberInput(
                    phone = state.phone,
                    onPhoneChange = onPhoneChange,
                    standardPadding = standardPadding
                )

                ConfirmBox(
                    text = stringResource(R.string.text_next_button),
                    onClick = { onNextClick(state.phone) }
                )
            }
        }
    }
}

@Composable
private fun HeaderSection(standardPadding: Dp) {
    Text(
        text = stringResource(id = R.string.text_title_input_phone_number),
        color = White,
        style = Typography.titleLargeB,
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(start = standardPadding, bottom = 20.dp)
    )
    
    Text(
        text = stringResource(id = R.string.text_guide_input_phone_number),
        color = Color.Gray,
        style = Typography.titleMediumM,
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(start = standardPadding)
    )

    Spacer(modifier = Modifier.height(30.dp))
}

@Composable
private fun CountrySection(standardPadding: Dp) {
    Text(
        text = "대한민국(Republic of Korea)",
        color = White,
        style = Typography.titleSmallM,
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(
            start = standardPadding,
            top = 10.dp,
            bottom = 20.dp
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PhoneNumberInput(
    phone: String,
    onPhoneChange: (String) -> Unit,
    standardPadding: Dp
) {
    TextField(
        value = phone,
        onValueChange = { textValue -> onPhoneChange(textValue) },
        placeholder = {
            Text(
                stringResource(id = R.string.text_title_input_phone_number),
                color = Color.Gray,
                style = Typography.bodyMediumR
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        modifier = Modifier
            .padding(
                start = standardPadding,
                end = standardPadding,
                bottom = 20.dp
            )
            .fillMaxWidth()
            .height(60.dp)
    )
}