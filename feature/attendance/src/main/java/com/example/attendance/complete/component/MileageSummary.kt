package com.example.attendance.complete.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.PilatesTheme
import com.example.designsystem.theme.Typography
import com.example.feature.attendance.R

@Composable
fun MileageSummary(
    mileage: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
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

@Preview(device = Devices.TABLET)
@Composable
private fun MileageSummaryPreview() {
    PilatesTheme {
        MileageSummary(mileage = 12)
    }
}
