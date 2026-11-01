package com.example.member.mileage

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
import com.example.member.component.MemberSummary
import com.example.designsystem.component.ValueStepper
import com.example.domain.model.User
import com.example.feature.member.R
import com.example.navigation.Route
import com.example.member.search.SearchUserViewModel

@Composable
fun ChangeUserMileageScreen(
    navController: NavHostController,
    viewModel: ChangeUserMileageViewModel = hiltViewModel(),
    searchUserViewModel: SearchUserViewModel = hiltViewModel(navController.getBackStackEntry(Route.SearchUser)),
    context: Context
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    val effectFlow = viewModel.effect

    LaunchedEffect(effectFlow) {
        effectFlow.collect { effect ->
            when (effect) {
                is ChangeUserMileageContract.Effect.CompleteChangeMileage -> {
                    searchUserViewModel.updateSearchedUserMileage(effect.mileage)
                    navController.popBackStack()
                }

                is ChangeUserMileageContract.Effect.ShowToast -> {
                    context.showToast(effect.msg)
                }
            }
        }
    }

    state.user?.let { user ->
        ChangeUserMileageContent(
            user = user,
            isLoading = state.isLoading,
            onBack = { navController.popBackStack() },
            onMileageUp = { viewModel.setEvent(ChangeUserMileageContract.Event.OnClickMileageUp) },
            onMileageDown = { viewModel.setEvent(ChangeUserMileageContract.Event.OnClickMileageDown) },
            onSave = {
                viewModel.setEvent(
                    ChangeUserMileageContract.Event.OnClickSaveButton(
                        user.phoneNumber,
                        user.mileage
                    )
                )
            }
        )
    }
}

@Composable
private fun ChangeUserMileageContent(
    user: User,
    isLoading: Boolean,
    onBack: () -> Unit,
    onMileageUp: () -> Unit,
    onMileageDown: () -> Unit,
    onSave: () -> Unit
) {
    PilatesScaffold(
        title = stringResource(id = R.string.text_menu_change_mileage),
        isLoading = isLoading,
        onBack = onBack
    ) {
        MemberSummary(
            name = user.name,
            phone = user.phoneNumber
        )

        Spacer(modifier = Modifier.height(16.dp))

        ValueStepper(
            title = stringResource(R.string.text_user_mileage, user.name),
            value = user.mileage,
            unit = stringResource(R.string.text_unit_point),
            canDecrease = user.mileage > 0,
            onIncrease = onMileageUp,
            onDecrease = onMileageDown
        )

        Spacer(modifier = Modifier.height(24.dp))

        ConfirmButton(
            text = stringResource(R.string.text_save_button),
            onClick = onSave
        )
    }
}
