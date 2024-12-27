package com.example.cmong_pilates_attendance_project.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun inputText(
    titleText: String,
    imageVector: ImageVector,
    contentText: String,
    handleOnClick: () -> Unit
) {

    Text(
        text = titleText,
        color = Color.White,
        fontSize = 30.sp,
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(top = 20.dp, start = 70.dp, bottom = 10.dp)
    )
    Box(
        modifier = Modifier
            .padding(start = 70.dp, end = 70.dp)
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(0xFFE8E0ED))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = handleOnClick
            )
    ) {
        Text(
            text = contentText,
            color = Color.Black,
            fontSize = 15.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 12.dp)
        )

        IconButton(
            onClick = handleOnClick
            ,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector, "backIcon", tint = Color.Black
            )
        }
    }
}
