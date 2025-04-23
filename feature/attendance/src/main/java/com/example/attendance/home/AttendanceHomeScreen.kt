package com.example.attendance.home

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.core_android.navigation.Route
import com.example.core_android.utils.showToast
import com.example.designsystem.theme.DarkGray
import com.example.designsystem.theme.Typography
import com.example.designsystem.theme.White
import com.example.feature.attendance.R

@Composable
fun AttendanceHomeScreen(
    navController: NavHostController,
    viewModel: AttendanceHomeViewModel = hiltViewModel(),
    context: Context
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    val effectFlow = viewModel.effect

    LaunchedEffect(effectFlow) {
        effectFlow.collect { effect ->
            when (effect) {
                is AttendanceHomeContract.Effect.GoAdminPage -> {
                    navController.navigate(Route.AdminMenu)
                }
                is AttendanceHomeContract.Effect.ShowToast -> {
                    context.showToast(effect.msg)
                }
                is AttendanceHomeContract.Effect.SuccessAttendance -> {
                    navController.navigate(Route.AttendanceComplete(effect.user.name, effect.user.mileage))
                }
            }
        }
    }

    AttendanceScreen(
        state = state,
        onAdminClick = { viewModel.setEvent(AttendanceHomeContract.Event.OnClickAdminMenu) },
        onNumberClick = { number -> viewModel.setEvent(AttendanceHomeContract.Event.OnClickNumber(number)) },
        onDeleteClick = { viewModel.setEvent(AttendanceHomeContract.Event.OnClickDelete) },
        onEnterClick = { phoneNumber -> viewModel.setEvent(AttendanceHomeContract.Event.OnClickOK(phoneNumber)) }
    )
}

@Composable
private fun AttendanceScreen(
    state: AttendanceHomeContract.State,
    onAdminClick: () -> Unit,
    onNumberClick: (String) -> Unit,
    onDeleteClick: () -> Unit,
    onEnterClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .horizontalScroll(rememberScrollState())
            .clipToBounds()
            .background(DarkGray)
    ) {
        // 관리자 버튼
        AdminButton(
            onAdminClick = onAdminClick,
            iconColor = White
        )

        // 로고 및 안내문구 영역
        LogoSection(textColor = White)

        // 전화번호 입력 및 키패드 영역
        PhoneInputSection(
            phoneNumber = state.phoneNumber,
            textColor = White,
            onNumberClick = onNumberClick,
            onDeleteClick = onDeleteClick,
            onEnterClick = onEnterClick
        )

        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
private fun AdminButton(
    onAdminClick: () -> Unit,
    iconColor: Color
) {
    IconButton(
        onClick = onAdminClick,
        modifier = Modifier.padding(start = 20.dp, top = 30.dp)
    ) {
        Icon(
            Icons.Filled.Person,
            contentDescription = "admin",
            tint = iconColor,
            modifier = Modifier.size(45.dp)
        )
    }
}

@Composable
private fun LogoSection(textColor: Color) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .clipToBounds()
            .width(450.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 로고
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(300.dp)
                    .width(450.dp)
            )

            // 안내 문구
            Text(
                text = stringResource(R.string.msg_info_pilates),
                color = textColor,
                style = Typography.titleMediumM,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}

@Composable
private fun PhoneInputSection(
    phoneNumber: String,
    textColor: Color,
    onNumberClick: (String) -> Unit,
    onDeleteClick: () -> Unit,
    onEnterClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .clipToBounds()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // 전화번호 입력 안내
        Text(
            text = stringResource(R.string.msg_input_phone_number),
            color = textColor,
            style = Typography.titleLargeM,
            textAlign = TextAlign.Left
        )

        Spacer(modifier = Modifier.height(10.dp))

        // 전화번호 표시 영역
        PhoneNumberDisplay(phoneNumber = phoneNumber)

        Spacer(modifier = Modifier.height(10.dp))

        // 키패드
        NumberKeypad(
            phoneNumber = phoneNumber,
            textColor = textColor,
            onNumberClick = onNumberClick,
            onDeleteClick = onDeleteClick,
            onEnterClick = onEnterClick
        )
    }
}

@Composable
private fun PhoneNumberDisplay(phoneNumber: String) {
    Box(
        modifier = Modifier
            .background(White)
            .width(500.dp)
            .height(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = phoneNumber,
            color = Color.Black,
            letterSpacing = 1.5.sp,
            style = Typography.labelLargeR,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun NumberKeypad(
    phoneNumber: String,
    textColor: Color,
    onNumberClick: (String) -> Unit,
    onDeleteClick: () -> Unit,
    onEnterClick: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .border(2.dp, textColor)
            .padding(10.dp)
            .width(400.dp)
    ) {
        // 첫 번째 줄: 7, 8, 9
        NumberRow(
            numbers = listOf("7", "8", "9"),
            textColor = textColor,
            onNumberClick = onNumberClick
        )

        // 두 번째 줄: 4, 5, 6
        NumberRow(
            numbers = listOf("4", "5", "6"),
            textColor = textColor,
            onNumberClick = onNumberClick
        )

        // 세 번째 줄: 1, 2, 3
        NumberRow(
            numbers = listOf("1", "2", "3"),
            textColor = textColor,
            onNumberClick = onNumberClick
        )

        // 네 번째 줄: 지우기, 0, 입장
        Row(verticalAlignment = Alignment.CenterVertically) {
            // 지우기 버튼
            DeleteButton(
                onDeleteClick = onDeleteClick,
                iconColor = textColor
            )

            // 0 버튼
            NumberKey(
                number = "0",
                textColor = textColor,
                onClick = onNumberClick
            )

            // 입장 버튼
            EnterButton(
                phoneNumber = phoneNumber,
                textColor = textColor,
                onEnterClick = onEnterClick
            )
        }
    }
}

@Composable
private fun NumberRow(
    numbers: List<String>,
    textColor: Color,
    onNumberClick: (String) -> Unit
) {
    Row {
        numbers.forEach { number ->
            NumberKey(
                number = number,
                textColor = textColor,
                onClick = onNumberClick
            )
        }
    }
}

@Composable
private fun NumberKey(
    number: String,
    textColor: Color,
    onClick: (String) -> Unit
) {
    Text(
        text = number,
        color = textColor,
        style = Typography.displayLargeR,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .width(120.dp)
            .height(110.dp)
            .wrapContentSize(align = Alignment.Center)
            .clickable { onClick(number) }
    )
}

@Composable
private fun DeleteButton(
    onDeleteClick: () -> Unit,
    iconColor: Color
) {
    IconButton(
        onClick = onDeleteClick,
        modifier = Modifier
            .width(120.dp)
            .height(110.dp)
    ) {
        Icon(
            Icons.Filled.ArrowBack,
            contentDescription = "delete",
            tint = iconColor,
            modifier = Modifier
                .width(90.dp)
                .height(100.dp)
        )
    }
}

@Composable
private fun EnterButton(
    phoneNumber: String,
    textColor: Color,
    onEnterClick: (String) -> Unit
) {
    Text(
        text = "입장",
        color = textColor,
        style = Typography.displayMediumR,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .wrapContentSize(align = Alignment.Center)
            .width(120.dp)
            .clickable { onEnterClick(phoneNumber) }
    )
}
