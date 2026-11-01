package com.example.registration.register

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.common.showToast
import com.example.designsystem.component.ConfirmButton
import com.example.designsystem.component.PilatesScaffold
import com.example.designsystem.component.DatePickerDialog
import com.example.designsystem.component.DurationPickerDialog
import com.example.designsystem.component.LabeledPickerField
import com.example.designsystem.component.LabeledTextField
import com.example.domain.model.Duration
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
private fun UserRegistrationScreen(
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

/** Two columns: who the member is on the left, when they can come on the right. */
@Composable
private fun UserInputForm(
    state: RegisterUserContract.State,
    onNameChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onDurationClick: () -> Unit,
    onStartDateClick: () -> Unit,
    onSaveClick: (String, String, Duration?, String) -> Unit
) {
    val fieldsEnabled = !state.isShowDurationView

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                LabeledTextField(
                    titleText = stringResource(R.string.text_input_name),
                    hintText = stringResource(R.string.text_input_hint_name),
                    keyboardType = KeyboardType.Text,
                    enabled = fieldsEnabled,
                    content = state.name,
                    onValueChange = onNameChange
                )
                LabeledTextField(
                    titleText = stringResource(R.string.text_input_phone_number),
                    hintText = stringResource(R.string.text_input_hint_phone_number),
                    keyboardType = KeyboardType.Number,
                    enabled = fieldsEnabled,
                    content = state.phone,
                    onValueChange = onPhoneChange
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                LabeledPickerField(
                    titleText = stringResource(id = R.string.text_input_duration),
                    imageVector = Icons.Filled.List,
                    contentText = state.selectedDuration?.label ?: "",
                    onClick = onDurationClick
                )
                LabeledPickerField(
                    titleText = stringResource(id = R.string.text_input_start_date),
                    imageVector = Icons.Filled.DateRange,
                    contentText = state.startDateText,
                    onClick = onStartDateClick
                )
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ConfirmButton(
                modifier = Modifier.width(360.dp),
                text = stringResource(R.string.text_save_button),
                onClick = {
                    onSaveClick(state.name, state.phone, state.selectedDuration, state.startDateText)
                }
            )
        }
    }
}
