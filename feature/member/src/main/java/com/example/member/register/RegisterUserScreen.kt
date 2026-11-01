package com.example.member.register

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.common.showToast
import com.example.designsystem.component.DatePickerDialog
import com.example.designsystem.component.DurationPickerDialog
import com.example.designsystem.component.PilatesScaffold
import com.example.designsystem.theme.PilatesTheme
import com.example.domain.model.Duration
import com.example.feature.member.R
import com.example.member.register.component.UserInputForm

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

    RegisterUserContent(
        state = state,
        onBack = { navController.popBackStack() },
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
                RegisterUserContract.Event.ClickedSave(name, phone, duration, startDate)
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

@Composable
private fun RegisterUserContent(
    state: RegisterUserContract.State,
    onBack: () -> Unit,
    onNameChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onDurationClick: () -> Unit,
    onStartDateClick: () -> Unit,
    onSaveClick: (String, String, Duration?, String) -> Unit,
    onDateSet: (Int, Int, Int) -> Unit,
    onDateDismiss: () -> Unit,
    onDurationSelected: (Duration) -> Unit,
    onDurationDismiss: () -> Unit,
    onDurationChange: (Duration) -> Unit
) {
    PilatesScaffold(
        title = stringResource(R.string.text_menu_register_user),
        isLoading = state.isLoading,
        onBack = onBack
    ) {
        UserInputForm(
            state = state,
            onNameChange = onNameChange,
            onPhoneChange = onPhoneChange,
            onDurationClick = onDurationClick,
            onStartDateClick = onStartDateClick,
            onSaveClick = onSaveClick
        )
    }

    DatePickerDialog(
        isShow = state.isShowStartDateView,
        startYear = state.startYear,
        startMonth = state.startMonth,
        startDay = state.startDay,
        onDataSetEvent = onDateSet,
        onDismissEvent = onDateDismiss
    )

    DurationPickerDialog(
        isShow = state.isShowDurationView,
        durationState = state.durationState,
        durationList = state.durationValues,
        label = { it.label },
        onClickDurationEvent = onDurationSelected,
        onDismissEvent = onDurationDismiss,
        onValueChange = onDurationChange
    )
}

@Preview(device = Devices.TABLET)
@Composable
private fun RegisterUserPreview() {
    PilatesTheme {
        RegisterUserContent(
            state = RegisterUserContract.State(),
            onBack = {},
            onNameChange = {},
            onPhoneChange = {},
            onDurationClick = {},
            onStartDateClick = {},
            onSaveClick = { _, _, _, _ -> },
            onDateSet = { _, _, _ -> },
            onDateDismiss = {},
            onDurationSelected = {},
            onDurationDismiss = {},
            onDurationChange = {}
        )
    }
}
