package com.example.cmong_pilates_attendance_project.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ConfirmBox(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(
                bottom = 30.dp, start = 60.dp, end = 60.dp,
                top = 30.dp
            )
            .clickable(
                onClick = onClick
            )
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(
                    Color(0xFF333333),
                    shape = RoundedCornerShape(12.dp)
                )
                .wrapContentSize(align = Alignment.Center)
        )
    }
}