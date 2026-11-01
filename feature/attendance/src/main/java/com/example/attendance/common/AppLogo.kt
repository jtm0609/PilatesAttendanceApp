package com.example.attendance.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.PilatesTheme
import com.example.feature.attendance.R

@Composable
fun AppLogo(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.app_logo),
        contentDescription = "HeilenPilates",
        contentScale = ContentScale.Fit
    )
}

@Preview(device = Devices.TABLET)
@Composable
private fun AppLogoPreview() {
    PilatesTheme {
        AppLogo(
            modifier = Modifier
                .width(380.dp)
                .height(240.dp)
        )
    }
}
