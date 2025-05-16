package com.example.registration.reregister

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.common.showToast
import com.example.domain.model.User
import com.example.designsystem.component.ConfirmButton
import com.example.designsystem.component.DatePickerDialog
import com.example.designsystem.component.DurationSettingBox
import com.example.designsystem.component.Toolbar
import com.example.designsystem.component.InputText
import com.example.designsystem.theme.DarkGray
import com.example.designsystem.theme.LightGray
import com.example.feature.registration.R
import com.example.navigation.Route
import com.example.search.SearchUserViewModel

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

    state.user?.let { user->
        ReRegisterUserContent(
            navController = navController,
            viewModel = viewModel,
            user = user
        )

        DurationSettingBox(
            isShow = state.isShowDurationView,
            onClickDurationEvent = { duration ->
                viewModel.setEvent(ReRegisterUserContract.Event.OnCompleteSetDuration(duration))
            },
            onDismissEvent = { viewModel.setEvent(ReRegisterUserContract.Event.OnDismissDurationView) },
            onValueChange = { duration ->
                viewModel.setEvent(ReRegisterUserContract.Event.OnChangedDuration(duration))
            },
            durationState = user.duration,
            durationList = state.durationValues
        )

        DatePickerDialog(
            isShow = state.isShowStartDateView,
            startYear = user.getStartDate().year,
            startMonth = user.getStartDate().month,
            startDay = user.getStartDate().day,
            onDataSetEvent = { year, month, day ->
                viewModel.setEvent(
                    ReRegisterUserContract.Event.OnCompleteStartDate(year, month, day)
                )
            }, onDismissEvent = {
                viewModel.setEvent(
                    ReRegisterUserContract.Event.OnDismissStartDate
                )
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReRegisterUserContent(
    navController: NavHostController,
    viewModel: ReRegisterUserViewModel,
    user: User
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = DarkGray,
        topBar = {
            Toolbar(
                navController,
                stringResource(id = R.string.text_menu_re_register_user)
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(it)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.TopCenter)
                ) {
                    Divider(
                        color = LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(bottom = 15.dp)
                    )
                    InputText(
                        titleText = stringResource(
                            id = R.string.text_input_duration
                        ),
                        imageVector = Icons.Filled.List,
                        contentText = user.duration,
                        handleOnClick = {
                            viewModel.setEvent(ReRegisterUserContract.Event.OnClickSetDuration)
                        }
                    )
                    InputText(
                        titleText = stringResource(
                            id = R.string.text_input_start_date
                        ),
                        imageVector = Icons.Filled.DateRange,
                        contentText = user.getStartDateText(),
                        handleOnClick = {
                            viewModel.setEvent(ReRegisterUserContract.Event.OnClickSetBeginDate)
                        }
                    )

                    ConfirmButton(
                        text = stringResource(R.string.text_save_button),
                        onClick = {
                            viewModel.setEvent(
                                ReRegisterUserContract.Event.ClickedSave(
                                    user
                                )
                            )
                        }
                    )
                }
            }
        }
    )
}