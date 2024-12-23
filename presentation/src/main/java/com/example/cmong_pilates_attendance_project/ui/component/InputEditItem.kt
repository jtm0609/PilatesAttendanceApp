package com.example.cmong_pilates_attendance_project.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.utils.Constant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun inputEditItem(
    titleText: String,
    hintText: String,
    keyboardType: KeyboardType,
    content: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean
) {

    val text by remember {
        mutableStateOf(content)
    }

    TextField(
        value = text,
        onValueChange = (onValueChange),
        placeholder = {
            Text(
                hintText,
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.CenterStart)
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ), modifier = Modifier
            .padding(start = 70.dp, end = 70.dp)
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(0XFFE7E7E7)),
        enabled = enabled
    )
}