package com.example.attendance.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.attendance.home.AttendanceHomeViewModel.Companion.PHONE_DIGITS
import com.example.designsystem.theme.PilatesTheme
import com.example.designsystem.theme.Typography

/**
 * Shows the eight digits typed so far, with empty slots drawn as dots,
 * so the member can see how many are left.
 */
@Composable
fun PhoneNumberDisplay(phoneNumber: String) {
    val text = buildAnnotatedString {
        for (index in 0 until PHONE_DIGITS) {
            if (index == PHONE_DIGITS / 2) append("  ")
            val digit = phoneNumber.getOrNull(index)
            if (digit != null) {
                withStyle(SpanStyle(color = PilatesTheme.colors.textPrimary)) { append(digit) }
            } else {
                withStyle(SpanStyle(color = PilatesTheme.colors.textDisabled)) { append("•") }
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = PilatesTheme.colors.background,
        shape = RoundedCornerShape(16.dp),
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = Typography.phoneDigits,
                maxLines = 1
            )
        }
    }
}
