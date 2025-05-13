package com.example.registration.register

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.common.showToast
import com.example.designsystem.component.ConfirmBox
import com.example.designsystem.component.DatePickerDialog
import com.example.designsystem.component.DurationSettingBox
import com.example.designsystem.component.Toolbar
import com.example.designsystem.component.InputTextField
import com.example.designsystem.component.InputText
import com.example.designsystem.component.Progress
import com.example.designsystem.theme.DarkGray
import com.example.designsystem.theme.LightGray
import com.example.feature.registration.R

@Composable
fun RegisterUserScreen(
    navController: NavHostController,
    viewModel: RegisterUserViewModel = hiltViewModel(),
    context: Context
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
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

    UserRegistrationScreen(
        navController = navController,
        state = state,
        onNameChange = { name ->
            viewModel.setEvent(RegisterUserContract.Event.OnChangedName(name))
        },
        onPhoneChange = { phone ->
            viewModel.setEvent(RegisterUserContract.Event.OnChangePhoneNumber(phone))
        },
        onDurationClick = {
            viewModel.setEvent(RegisterUserContract.Event.OnClickSetDuration)
        },
        onStartDateClick = {
            viewModel.setEvent(RegisterUserContract.Event.OnClickSetBeginDate)
        },
        onSaveClick = { name, phone, duration, startDate ->
            viewModel.setEvent(
                RegisterUserContract.Event.ClickedSave(
                    name,
                    phone,
                    duration,
                    startDate
                )
            )
        },
        onDateSet = { year, month, day ->
            viewModel.setEvent(RegisterUserContract.Event.OnCompleteStartDate(year, month, day))
        },
        onDateDismiss = {
            viewModel.setEvent(RegisterUserContract.Event.OnDismissStartDate)
        },
        onDurationSelected = { duration ->
            viewModel.setEvent(RegisterUserContract.Event.OnCompleteSetDuration(duration))
        },
        onDurationDismiss = {
            viewModel.setEvent(RegisterUserContract.Event.OnDismissDurationView)
        },
        onDurationChange = { duration ->
            viewModel.setEvent(RegisterUserContract.Event.OnChangeDuration(duration))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserRegistrationScreen(
    navController: NavHostController,
    state: RegisterUserContract.State,
    onNameChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onDurationClick: () -> Unit,
    onStartDateClick: () -> Unit,
    onSaveClick: (String, String, String, String) -> Unit,
    onDateSet: (Int, Int, Int) -> Unit,
    onDateDismiss: () -> Unit,
    onDurationSelected: (String) -> Unit,
    onDurationDismiss: () -> Unit,
    onDurationChange: (String) -> Unit
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = DarkGray,
        topBar = { Toolbar(navController, stringResource(R.string.text_menu_register_user)) }
    ) { paddingValues ->
        if (state.isLoading) {
            Progress()
        }

        Box(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .fillMaxWidth()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { }
        ) {
            UserInputForm(
                state = state,
                dividerColor = LightGray,
                onNameChange = onNameChange,
                onPhoneChange = onPhoneChange,
                onDurationClick = onDurationClick,
                onStartDateClick = onStartDateClick,
                onSaveClick = onSaveClick
            )
        }
    }

    // 날짜 선택 다이얼로그
    DatePickerDialog(
        isShow = state.isShowStartDateView,
        startYear = state.startYear,
        startMonth = state.startMonth,
        startDay = state.startDay,
        onDataSetEvent = onDateSet,
        onDismissEvent = onDateDismiss
    )

    // 기간 설정 다이얼로그
    DurationSettingBox(
        isShow = state.isShowDurationView,
        onClickDurationEvent = onDurationSelected,
        onDismissEvent = onDurationDismiss,
        onValueChange = onDurationChange,
        durationState = state.durationState,
        durationList = state.durationValues
    )
}

@Composable
private fun UserInputForm(
    state: RegisterUserContract.State,
    dividerColor: Color,
    onNameChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onDurationClick: () -> Unit,
    onStartDateClick: () -> Unit,
    onSaveClick: (String, String, String, String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Divider(
            color = dividerColor,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
        )

        // 이름 입력 필드
        NameInputField(
            name = state.name,
            onNameChange = onNameChange,
            isEnabled = !state.isShowDurationView
        )
        
        // 전화번호 입력 필드
        PhoneInputField(
            phone = state.phone,
            onPhoneChange = onPhoneChange,
            isEnabled = !state.isShowDurationView
        )
        
        // 이용 기간 선택 필드
        DurationSelectionField(
            durationText = state.durationText,
            onDurationClick = onDurationClick
        )
        
        // 시작일 선택 필드
        StartDateSelectionField(
            startDateText = state.startDateText,
            onStartDateClick = onStartDateClick
        )

        // 저장 버튼
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            SaveButton(
                onSaveClick = {
                    onSaveClick(state.name, state.phone, state.durationText, state.startDateText)
                }
            )
        }
    }
}

@Composable
private fun NameInputField(
    name: String,
    onNameChange: (String) -> Unit,
    isEnabled: Boolean
) {
    InputTextField(
        titleText = stringResource(R.string.text_input_name),
        hintText = stringResource(R.string.text_input_hint_name),
        keyboardType = KeyboardType.Text,
        content = name,
        onValueChange = onNameChange,
        enabled = isEnabled
    )
}

@Composable
private fun PhoneInputField(
    phone: String,
    onPhoneChange: (String) -> Unit,
    isEnabled: Boolean
) {
    InputTextField(
        titleText = stringResource(R.string.text_input_phone_number),
        hintText = stringResource(R.string.text_input_hint_phone_number),
        keyboardType = KeyboardType.Number,
        content = phone,
        onValueChange = onPhoneChange,
        enabled = isEnabled
    )
}

@Composable
private fun DurationSelectionField(
    durationText: String,
    onDurationClick: () -> Unit
) {
    InputText(
        titleText = stringResource(id = R.string.text_input_duration),
        imageVector = Icons.Filled.List,
        contentText = durationText,
        handleOnClick = onDurationClick
    )
}

@Composable
private fun StartDateSelectionField(
    startDateText: String,
    onStartDateClick: () -> Unit
) {
    InputText(
        titleText = stringResource(id = R.string.text_input_start_date),
        imageVector = Icons.Filled.DateRange,
        contentText = startDateText,
        handleOnClick = onStartDateClick
    )
}

@Composable
private fun SaveButton(
    onSaveClick: () -> Unit
) {
    ConfirmBox(
        text = stringResource(R.string.text_save_button),
        onClick = onSaveClick
    )
}