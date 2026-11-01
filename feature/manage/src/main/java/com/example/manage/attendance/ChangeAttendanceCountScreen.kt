package com.example.manage.attendance

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.common.showToast
import com.example.designsystem.component.ConfirmButton
import com.example.designsystem.component.PilatesScaffold
import com.example.designsystem.component.ValueStepper
import com.example.feature.manage.R

@Composable
fun ChangeAttendanceCountScreen(
    navController: NavHostController,
    viewModel: ChangeAttendanceCountViewModel = hiltViewModel(),
    context: Context
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    val effectFlow = viewModel.effect

    LaunchedEffect(effectFlow) {
        effectFlow.collect { effect ->
            when (effect) {
                is ChangeAttendanceCountContract.Effect.CompleteChangeMileage -> {
                    navController.popBackStack()
                }
                is ChangeAttendanceCountContract.Effect.ShowToast -> {
                    context.showToast(effect.msg)
                }
            }
        }
    }

    AttendanceCountScreen(
        attendanceCount = state.attendanceCount,
        isLoading = state.isLoading,
        onBack = { navController.popBackStack() },
        onCountUp = { viewModel.setEvent(ChangeAttendanceCountContract.Event.OnClickCountUp) },
        onCountDown = { viewModel.setEvent(ChangeAttendanceCountContract.Event.OnClickCountDown) },
        onSave = { count ->
            viewModel.setEvent(ChangeAttendanceCountContract.Event.OnClickSaveButton(count))
        }
    )
}

@Composable
private fun AttendanceCountScreen(
    attendanceCount: Int,
    isLoading: Boolean,
    onBack: () -> Unit,
    onCountUp: () -> Unit,
    onCountDown: () -> Unit,
    onSave: (Int) -> Unit
) {
    PilatesScaffold(
        title = stringResource(R.string.text_menu_change_attendance_count),
        isLoading = isLoading,
        onBack = onBack
    ) {
        ValueStepper(
            title = stringResource(R.string.text_max_attendance_count),
            value = attendanceCount,
            unit = stringResource(R.string.text_unit_count),
            canDecrease = attendanceCount > 1,
            helper = stringResource(R.string.text_max_attendance_count_helper),
            onIncrease = onCountUp,
            onDecrease = onCountDown
        )

        Spacer(modifier = Modifier.height(24.dp))

        ConfirmButton(
            text = stringResource(R.string.text_save_button),
            onClick = { onSave(attendanceCount) }
        )
    }
}
