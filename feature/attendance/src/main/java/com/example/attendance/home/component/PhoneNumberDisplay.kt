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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designsystem.theme.White
import com.example.designsystem.theme.Typography

@Composable
fun PhoneNumberDisplay(phoneNumber: String) {
    Surface(
        color = White,
        shape = RoundedCornerShape(22.dp),
        shadowElevation = 2.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = phoneNumber,
                color = Color.Black,
                letterSpacing = 2.sp,
                style = Typography.bodyLargeR
            )
        }
    }
}