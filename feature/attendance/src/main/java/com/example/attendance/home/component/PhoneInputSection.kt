package com.example.attendance.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.attendance.home.AttendanceHomeViewModel.Companion.PHONE_DIGITS
import com.example.designsystem.theme.PilatesTheme
import com.example.designsystem.theme.Typography
import com.example.feature.attendance.R

/** The keypad card. Fills the height it is given so keys stay large. */
@Composable
fun PhoneInputSection(
    modifier: Modifier,
    phoneNumber: String,
    onNumberClick: (String) -> Unit,
    onBackspaceClick: () -> Unit,
    onEnterClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .widthIn(max = 480.dp),
            shape = RoundedCornerShape(28.dp),
            color = PilatesTheme.colors.surface,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 28.dp, vertical = 24.dp)
            ) {
                Text(
                    text = stringResource(R.string.msg_input_phone_number),
                    color = PilatesTheme.colors.textPrimary,
                    style = Typography.titleMediumM,
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = stringResource(R.string.msg_input_phone_number_sub),
                    color = PilatesTheme.colors.textSecondary,
                    style = Typography.bodyMediumR,
                )

                Spacer(modifier = Modifier.height(16.dp))

                PhoneNumberDisplay(phoneNumber = phoneNumber)

                Spacer(modifier = Modifier.height(16.dp))

                NumberKeypad(
                    modifier = Modifier.weight(1f),
                    enterEnabled = phoneNumber.length == PHONE_DIGITS,
                    onNumberClick = onNumberClick,
                    onBackspaceClick = onBackspaceClick,
                    onEnterClick = onEnterClick
                )
            }
        }
    }
}
