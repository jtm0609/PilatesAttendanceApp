package com.example.attendance.common

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
