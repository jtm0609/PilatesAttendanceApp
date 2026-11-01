package com.example.attendance.complete

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.designsystem.theme.PilatesTheme
import com.example.designsystem.theme.Typography
import com.example.feature.attendance.R
import kotlinx.coroutines.delay

private const val AUTO_RETURN_MS = 3000

@Composable
fun AttendanceCompleteScreen(
    navController: NavHostController,
    viewModel: AttendanceCompleteViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        delay(AUTO_RETURN_MS.toLong())
        navController.popBackStack()
    }

    AttendanceCompleteContent(state = state)
}

@Composable
fun AttendanceCompleteContent(
    state: AttendanceCompleteContract.State
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PilatesTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            CheckMark()

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = stringResource(R.string.msg_complete_attendance),
                color = PilatesTheme.colors.textSecondary,
                style = Typography.titleMediumM,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            MemberName(name = state.name)

            Spacer(modifier = Modifier.height(40.dp))

            MileageSummary(mileage = state.mileage)

            Spacer(modifier = Modifier.weight(1f))
        }

        AppLogo(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 24.dp, bottom = 16.dp)
        )

        AutoReturnBar(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
private fun CheckMark() {
    Box(
        modifier = Modifier
            .size(72.dp)
            .background(PilatesTheme.colors.primary, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = Icons.Filled.Check,
            contentDescription = null,
            tint = PilatesTheme.colors.onPrimary,
        )
    }
}

/** The member's name is the one loud thing on this screen. */
@Composable
private fun MemberName(name: String) {
    Row(verticalAlignment = Alignment.Bottom) {
        Text(
            text = name,
            color = PilatesTheme.colors.textPrimary,
            style = Typography.displaySerif,
            maxLines = 1
        )
        Text(
            modifier = Modifier.padding(start = 10.dp, bottom = 18.dp),
            text = stringResource(R.string.text_name_suffix),
            color = PilatesTheme.colors.textPrimary,
            style = Typography.headlineLargeR,
        )
    }
}

@Composable
private fun MileageSummary(mileage: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .border(1.dp, PilatesTheme.colors.primary, RoundedCornerShape(999.dp))
                .padding(horizontal = 18.dp, vertical = 10.dp)
        ) {
            Text(
                text = stringResource(R.string.msg_add_mileage),
                color = PilatesTheme.colors.primary,
                style = Typography.labelMediumR
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        Row(
            modifier = Modifier
                .background(PilatesTheme.colors.surface, RoundedCornerShape(999.dp))
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.msg_current_mileage),
                color = PilatesTheme.colors.textSecondary,
                style = Typography.labelMediumR
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(R.string.msg_mileage_point, mileage),
                color = PilatesTheme.colors.textPrimary,
                style = Typography.titleMediumB.copy(fontWeight = FontWeight.SemiBold)
            )
        }
    }
}

/** Thin bar that drains over three seconds: the screen will go back on its own. */
@Composable
private fun AutoReturnBar(modifier: Modifier = Modifier) {
    var target by remember { mutableStateOf(1f) }
    val progress by animateFloatAsState(
        targetValue = target,
        animationSpec = tween(durationMillis = AUTO_RETURN_MS, easing = LinearEasing),
        label = "autoReturn"
    )
    LaunchedEffect(Unit) { target = 0f }

    LinearProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .height(4.dp),
        color = PilatesTheme.colors.primary,
        trackColor = PilatesTheme.colors.outline,
        progress = { progress }
    )
}

@Composable
private fun AppLogo(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .width(160.dp)
            .height(100.dp),
        painter = painterResource(id = R.drawable.app_logo),
        contentDescription = "HeilenPilates",
        contentScale = ContentScale.Fit,
    )
}

@Preview(device = Devices.TABLET)
@Composable
private fun AttendanceCompletePreview() {
    AttendanceCompleteContent(
        state = AttendanceCompleteContract.State(name = "김하늘", mileage = 12)
    )
}
