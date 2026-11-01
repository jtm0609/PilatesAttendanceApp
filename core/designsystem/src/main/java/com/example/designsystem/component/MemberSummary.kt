package com.example.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.R
import com.example.designsystem.theme.PilatesTheme
import com.example.designsystem.theme.Typography

@Composable
fun MemberSummary(
    name: String,
    phone: String,
    modifier: Modifier = Modifier,
    mileage: Int? = null
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = PilatesTheme.colors.surface,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .background(PilatesTheme.colors.primary, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name.take(1),
                    color = PilatesTheme.colors.onPrimary,
                    style = Typography.titleMediumB
                )
            }

            Spacer(modifier = Modifier.width(18.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    color = PilatesTheme.colors.textPrimary,
                    style = Typography.titleMediumM
                )
                Text(
                    text = formatPhone(phone),
                    color = PilatesTheme.colors.textSecondary,
                    style = Typography.bodyMediumR
                )
            }

            if (mileage != null) {
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = stringResource(R.string.text_owned_mileage),
                        color = PilatesTheme.colors.textSecondary,
                        style = Typography.labelSmallR
                    )
                    Text(
                        text = stringResource(R.string.text_mileage_point, mileage),
                        color = PilatesTheme.colors.textPrimary,
                        style = Typography.titleLargeM.copy(fontWeight = FontWeight.SemiBold)
                    )
                }
            }
        }
    }
}

private fun formatPhone(raw: String): String {
    val digits = raw.filter { it.isDigit() }
    return if (digits.length == 11) {
        "${digits.substring(0, 3)}-${digits.substring(3, 7)}-${digits.substring(7)}"
    } else {
        raw
    }
}

@Preview(device = Devices.TABLET)
@Composable
private fun MemberSummaryPreview() {
    ComponentPreview {
        Column {
            MemberSummary(name = "김하늘", phone = "01012345678")
            Spacer(modifier = Modifier.height(16.dp))
            MemberSummary(name = "김하늘", phone = "01012345678", mileage = 12)
        }
    }
}
