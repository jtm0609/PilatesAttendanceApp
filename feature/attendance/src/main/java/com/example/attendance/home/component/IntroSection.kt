package com.example.attendance.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.attendance.common.AppLogo
import com.example.designsystem.theme.PilatesTheme
import com.example.designsystem.theme.Typography
import com.example.feature.attendance.R

@Composable
fun IntroSection(
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AppLogo(
            modifier = Modifier
                .width(380.dp)
                .height(240.dp)
        )

        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = stringResource(R.string.msg_info_pilates),
            color = PilatesTheme.colors.textSecondary,
            style = Typography.bodyLargeR,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(device = Devices.TABLET)
@Composable
private fun IntroSectionPreview() {
    PilatesTheme {
        IntroSection(modifier = Modifier)
    }
}
