package com.example.member.search

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.common.showToast
import com.example.designsystem.component.ConfirmButton
import com.example.designsystem.component.LabeledTextField
import com.example.designsystem.component.PilatesScaffold
import com.example.designsystem.theme.PilatesTheme
import com.example.designsystem.theme.Typography
import com.example.feature.member.R
import com.example.navigation.Route

@Composable
fun SearchUserScreen(
    navController: NavHostController,
    viewModel: SearchUserViewModel = hiltViewModel(),
    context: Context,
    hideKeyboard: () -> Unit
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    val effectFlow = viewModel.effect

    LaunchedEffect(effectFlow) {
        effectFlow.collect { effect ->
            when (effect) {
                is SearchUserContract.Effect.CompleteSearch -> {
                    navController.navigate(Route.ManageUser)
                }
                is SearchUserContract.Effect.ShowToast -> {
                    context.showToast(effect.msg)
                }
            }
        }
    }

    PhoneNumberInputScreen(
        state = state,
        onBack = { navController.popBackStack() },
        onPhoneChange = { phoneNumber ->
            viewModel.setEvent(SearchUserContract.Event.OnChangePhoneNumber(phoneNumber))
        },
        onNextClick = { phoneNumber ->
            hideKeyboard()
            viewModel.setEvent(SearchUserContract.Event.OnClickNextButton(phoneNumber))
        },
        hideKeyboard = hideKeyboard
    )
}

@Composable
private fun PhoneNumberInputScreen(
    state: SearchUserContract.State,
    onBack: () -> Unit,
    onPhoneChange: (String) -> Unit,
    onNextClick: (String) -> Unit,
    hideKeyboard: () -> Unit
) {
    PilatesScaffold(
        modifier = Modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            onClick = hideKeyboard
        ),
        title = stringResource(R.string.text_title_manage_user),
        isLoading = state.isLoading,
        onBack = onBack
    ) {
        Text(
            text = stringResource(id = R.string.text_title_input_phone_number),
            color = PilatesTheme.colors.textPrimary,
            style = Typography.headlineMediumM
        )
        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = stringResource(id = R.string.text_guide_input_phone_number),
            color = PilatesTheme.colors.textSecondary,
            style = Typography.bodyLargeR,
        )

        Spacer(modifier = Modifier.height(28.dp))

        LabeledTextField(
            titleText = stringResource(R.string.text_label_phone_number),
            hintText = stringResource(R.string.text_hint_phone_number),
            keyboardType = KeyboardType.Number,
            enabled = true,
            content = state.phone,
            onValueChange = onPhoneChange
        )

        Spacer(modifier = Modifier.height(28.dp))

        ConfirmButton(
            text = stringResource(R.string.text_next_button),
            onClick = { onNextClick(state.phone) }
        )
    }
}
