package com.example.attendance.complete.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.PilatesTheme
import com.example.designsystem.theme.Typography
import com.example.feature.attendance.R

@Composable
fun MemberName(
    name: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) {
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
            style = Typography.headlineLargeR
        )
    }
}

@Preview(device = Devices.TABLET)
@Composable
private fun MemberNamePreview() {
    PilatesTheme {
        MemberName(name = "김하늘")
    }
}
