package com.example.attendance.home.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.PilatesTheme
import com.example.designsystem.theme.Typography
import com.example.feature.attendance.R

/** Quiet entry to the admin menu. Members should barely notice it. */
@Composable
fun AdminButton(
    modifier: Modifier,
    onAdminClick: () -> Unit,
) {
    Surface(
        modifier = modifier.padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        color = Color.Transparent,
        contentColor = PilatesTheme.colors.textDisabled,
        onClick = onAdminClick
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Filled.Settings,
                contentDescription = stringResource(R.string.text_admin),
                tint = PilatesTheme.colors.textDisabled,
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = stringResource(R.string.text_admin),
                color = PilatesTheme.colors.textDisabled,
                style = Typography.labelSmallR
            )
        }
    }
}

@Preview(device = Devices.TABLET)
@Composable
private fun AdminButtonPreview() {
    PilatesTheme {
        AdminButton(modifier = Modifier, onAdminClick = {})
    }
}
