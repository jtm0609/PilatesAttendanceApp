package com.example.member.reregister

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.common.showToast
import com.example.designsystem.component.ConfirmButton
import com.example.designsystem.component.DatePickerDialog
import com.example.designsystem.component.DurationPickerDialog
import com.example.designsystem.component.LabeledPickerField
import com.example.designsystem.component.PilatesScaffold
import com.example.designsystem.theme.PilatesTheme
import com.example.domain.model.Duration
import com.example.domain.model.User
import com.example.feature.member.R
import com.example.member.component.MemberSummary
import com.example.member.search.SearchUserViewModel
import com.example.navigation.Route

@Composable
fun ReRegisterUserScreen(
    navController: NavHostController,
    viewModel: ReRegisterUserViewModel = hiltViewModel(),
    searchUserViewModel: SearchUserViewModel = hiltViewModel(navController.getBackStackEntry(Route.SearchUser)),
    context: Context
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    val effectFlow = viewModel.effect

    LaunchedEffect(effectFlow) {
        effectFlow.collect { effect ->
            when (effect) {
                is ReRegisterUserContract.Effect.ShowToast -> {
                    context.showToast(effect.msg)
                }
                is ReRegisterUserContract.Effect.CompleteRegister -> {
                    searchUserViewModel.updateSearchedUserUsingDate(
                        effect.user.startDateTime,
                        effect.user.endDateTime,
                        effect.user.duration
                    )
                    navController.popBackStack()
                }
            }
        }
    }

    state.user?.let { user ->
        ReRegisterUserContent(
            user = user,
            isLoading = state.isLoading,
            onBack = { navController.popBackStack() },
            onDurationClick = {
                viewModel.setEvent(ReRegisterUserContract.Event.OnClickSetDuration)
            },
            onStartDateClick = {
                viewModel.setEvent(ReRegisterUserContract.Event.OnClickSetBeginDate)
            },
            onSaveClick = {
                viewModel.setEvent(ReRegisterUserContract.Event.ClickedSave(user))
            }
        )

        DurationPickerDialog(
            isShow = state.isShowDurationView,
            durationState = Duration.fromLabel(user.duration) ?: Duration.TWO_WEEKS,
            durationList = state.durationValues,
            label = { it.label },
            onClickDurationEvent = { duration ->
                viewModel.setEvent(ReRegisterUserContract.Event.OnCompleteSetDuration(duration))
            },
            onDismissEvent = {
                viewModel.setEvent(ReRegisterUserContract.Event.OnDismissDurationView)
            },
            onValueChange = { duration ->
                viewModel.setEvent(ReRegisterUserContract.Event.OnChangedDuration(duration))
            }
        )

        DatePickerDialog(
            isShow = state.isShowStartDateView,
            startYear = user.getStartDate().year,
            startMonth = user.getStartDate().month,
            startDay = user.getStartDate().day,
            onDataSetEvent = { year, month, day ->
                viewModel.setEvent(ReRegisterUserContract.Event.OnCompleteStartDate(year, month, day))
            },
            onDismissEvent = {
                viewModel.setEvent(ReRegisterUserContract.Event.OnDismissStartDate)
            }
        )
    }
}

@Composable
private fun ReRegisterUserContent(
    user: User,
    isLoading: Boolean,
    onBack: () -> Unit,
    onDurationClick: () -> Unit,
    onStartDateClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    PilatesScaffold(
        title = stringResource(id = R.string.text_menu_re_register_user),
        isLoading = isLoading,
        onBack = onBack
    ) {
        MemberSummary(
            name = user.name,
            phone = user.phoneNumber
        )

        Spacer(modifier = Modifier.height(28.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            LabeledPickerField(
                modifier = Modifier.weight(1f),
                titleText = stringResource(id = R.string.text_input_duration),
                imageVector = Icons.Filled.List,
                contentText = user.duration,
                onClick = onDurationClick,
            )
            LabeledPickerField(
                modifier = Modifier.weight(1f),
                titleText = stringResource(id = R.string.text_input_start_date),
                imageVector = Icons.Filled.DateRange,
                contentText = user.getStartDateText(),
                onClick = onStartDateClick,
            )
        }

        Spacer(modifier = Modifier.height(36.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ConfirmButton(
                modifier = Modifier.width(360.dp),
                text = stringResource(R.string.text_save_button),
                onClick = onSaveClick
            )
        }
    }
}

@Preview(device = Devices.TABLET)
@Composable
private fun ReRegisterUserPreview() {
    PilatesTheme {
        ReRegisterUserContent(
            user = User(
                id = 1,
                name = "김하늘",
                phoneNumber = "01012345678",
                duration = "4주",
                startDateTime = 1_767_225_600_000L,
                endDateTime = 1_769_731_200_000L,
                mileage = 12,
                attendanceCountOfToday = 0,
                attendanceDate = 0L
            ),
            isLoading = false,
            onBack = {},
            onDurationClick = {},
            onStartDateClick = {},
            onSaveClick = {}
        )
    }
}
