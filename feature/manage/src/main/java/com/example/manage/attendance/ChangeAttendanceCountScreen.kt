package com.example.manage.attendance

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
import androidx.compose.material3.MaterialTheme
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.core_android.utils.showToast
import com.example.designsystem.component.ConfirmBox
import com.example.designsystem.component.Toolbar
import com.example.designsystem.component.Progress
import com.example.designsystem.theme.DarkGray
import com.example.designsystem.theme.LightGray
import com.example.designsystem.theme.Typography
import com.example.designsystem.theme.White
import com.example.feature.manage.R

@Composable
fun ChangeAttendanceCountScreen(
    navController: NavHostController,
    viewModel: ChangeAttendanceCountViewModel,
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
        navController = navController,
        attendanceCount = state.attendanceCount,
        isLoading = state.isLoading,
        onCountUp = { 
            viewModel.setEvent(ChangeAttendanceCountContract.Event.OnClickCountUp)
        },
        onCountDown = { 
            viewModel.setEvent(ChangeAttendanceCountContract.Event.OnClickCountDown)
        },
        onSave = { count ->
            viewModel.setEvent(ChangeAttendanceCountContract.Event.OnClickSaveButton(count))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AttendanceCountScreen(
    navController: NavHostController,
    attendanceCount: Int,
    isLoading: Boolean,
    onCountUp: () -> Unit,
    onCountDown: () -> Unit,
    onSave: (Int) -> Unit
) {
    val textColor = White
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = DarkGray,
        topBar = {
            Toolbar(
                navController = navController,
                titleText = stringResource(R.string.text_menu_change_attendance_count)
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

            AttendanceCountCard(
                attendanceCount = attendanceCount,
                cardColor = LightGray,
                textColor = textColor,
                onCountUp = onCountUp,
                onCountDown = onCountDown
            )
            
            SaveButton(
                onSave = { onSave(attendanceCount) }
            )
        }
    }
}

@Composable
private fun AttendanceCountCard(
    attendanceCount: Int,
    cardColor: Color,
    textColor: Color,
    onCountUp: () -> Unit,
    onCountDown: () -> Unit
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
                HeaderText(textColor = textColor)
                
                Spacer(modifier = Modifier.height(10.dp))
                
                CountControls(
                    count = attendanceCount,
                    textColor = textColor,
                    onCountUp = onCountUp,
                    onCountDown = onCountDown
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
private fun HeaderText(textColor: Color) {
    Text(
        text = stringResource(R.string.text_max_attendance_count),
        color = textColor,
        style = Typography.titleLargeM,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun CountControls(
    count: Int,
    textColor: Color,
    onCountUp: () -> Unit,
    onCountDown: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.text_attendance_count, count),
            color = textColor,
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.width(20.dp))
        
        CountControlButton(
            text = "↑",
            onClick = onCountUp,
            textColor = textColor
        )
        
        Spacer(modifier = Modifier.width(10.dp))
        
        CountControlButton(
            text = "↓",
            onClick = onCountDown,
            textColor = textColor
        )
    }
}

@Composable
private fun CountControlButton(
    text: String,
    onClick: () -> Unit,
    textColor: Color
) {
    Text(
        text = text,
        color = textColor,
        style = MaterialTheme.typography.displayLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier.clickable(onClick = onClick)
    )
}

@Composable
private fun SaveButton(onSave: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        ConfirmBox(
            text = stringResource(R.string.text_save_button),
            onClick = onSave
        )
    }
}