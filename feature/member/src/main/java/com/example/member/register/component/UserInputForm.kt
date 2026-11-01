package com.example.member.register.component

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.component.ConfirmButton
import com.example.designsystem.component.LabeledPickerField
import com.example.designsystem.component.LabeledTextField
import com.example.designsystem.theme.PilatesTheme
import com.example.domain.model.Duration
import com.example.feature.member.R
import com.example.member.register.RegisterUserContract

@Composable
fun UserInputForm(
    state: RegisterUserContract.State,
    modifier: Modifier = Modifier,
    onNameChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onDurationClick: () -> Unit,
    onStartDateClick: () -> Unit,
    onSaveClick: (String, String, Duration?, String) -> Unit
) {
    val fieldsEnabled = !state.isShowDurationView

    Column(modifier = modifier.fillMaxWidth()) {
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

@Preview(device = Devices.TABLET)
@Composable
private fun UserInputFormPreview() {
    PilatesTheme {
        UserInputForm(
            state = RegisterUserContract.State(
                name = "김하늘",
                phone = "01012345678",
                selectedDuration = Duration.FOUR_WEEKS,
                startDateText = "2026-01-01"
            ),
            onNameChange = {},
            onPhoneChange = {},
            onDurationClick = {},
            onStartDateClick = {},
            onSaveClick = { _, _, _, _ -> }
        )
    }
}
