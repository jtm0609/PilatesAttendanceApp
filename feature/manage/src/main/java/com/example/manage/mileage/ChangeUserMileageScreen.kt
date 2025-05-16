package com.example.manage.mileage

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.common.showToast
import com.example.domain.model.User
import com.example.designsystem.component.ConfirmButton
import com.example.designsystem.component.Toolbar
import com.example.designsystem.component.Progress
import com.example.designsystem.theme.DarkGray
import com.example.designsystem.theme.LightGray
import com.example.designsystem.theme.Typography
import com.example.designsystem.theme.White
import com.example.feature.manage.R
import com.example.navigation.Route
import com.example.search.SearchUserViewModel

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
        MileageChangeScreen(
            navController = navController,
            user = user,
            isLoading = state.isLoading,
            onMileageUp = {
                viewModel.setEvent(ChangeUserMileageContract.Event.OnClickMileageUp)
            },
            onMileageDown = {
                viewModel.setEvent(ChangeUserMileageContract.Event.OnClickMileageDown)
            },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MileageChangeScreen(
    navController: NavHostController,
    user: User,
    isLoading: Boolean,
    onMileageUp: () -> Unit,
    onMileageDown: () -> Unit,
    onSave: () -> Unit
) {
    val textColor = White

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = DarkGray,
        topBar = {
            Toolbar(
                navController = navController,
                titleText = stringResource(id = R.string.text_menu_change_mileage)
            )
        }
    ) { paddingValues ->
        if (isLoading) {
            Progress()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Divider(
                color = LightGray,
                thickness = 1.dp,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            MileageChangeCard(
                user = user,
                cardColor = LightGray,
                textColor = textColor,
                onMileageUp = onMileageUp,
                onMileageDown = onMileageDown
            )

            SaveButton(onSave = onSave)
        }
    }
}

@Composable
private fun MileageChangeCard(
    user: User,
    cardColor: Color,
    textColor: Color,
    onMileageUp: () -> Unit,
    onMileageDown: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .background(
                        cardColor,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(60.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeaderText(
                    userName = user.name,
                    textColor = textColor
                )

                Spacer(modifier = Modifier.height(10.dp))

                MileageControls(
                    mileage = user.mileage,
                    textColor = textColor,
                    onMileageUp = onMileageUp,
                    onMileageDown = onMileageDown
                )

                Divider(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(0.8f),
                    color = textColor.copy(alpha = 0.3f)
                )
            }
        }
    }
}

@Composable
private fun HeaderText(
    userName: String,
    textColor: Color
) {
    Text(
        text = stringResource(R.string.text_user_mileage, userName),
        color = textColor,
        style = Typography.titleLargeM,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun MileageControls(
    mileage: Int,
    textColor: Color,
    onMileageUp: () -> Unit,
    onMileageDown: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.text_user_score, mileage),
            color = textColor,
            style = Typography.displayLargeR,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.width(20.dp))

        MileageControlButton(
            text = "↑",
            onClick = onMileageUp,
            textColor = textColor
        )

        Spacer(modifier = Modifier.width(10.dp))

        MileageControlButton(
            text = "↓",
            onClick = onMileageDown,
            textColor = textColor
        )
    }
}

@Composable
private fun MileageControlButton(
    text: String,
    onClick: () -> Unit,
    textColor: Color
) {
    Text(
        text = text,
        color = textColor,
        style = Typography.displayLargeR,
        textAlign = TextAlign.Center,
        modifier = Modifier.clickable(onClick = onClick)
    )
}

@Composable
private fun SaveButton(onSave: () -> Unit) {
    ConfirmButton(
        text = stringResource(R.string.text_save_button),
        onClick = onSave
    )
}