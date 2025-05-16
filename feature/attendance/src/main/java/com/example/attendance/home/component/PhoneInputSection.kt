package com.example.attendance.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.feature.attendance.R
import com.example.designsystem.theme.Typography

@Composable
fun PhoneInputSection(
    modifier: Modifier,
    phoneNumber: String,
    onNumberClick: (String) -> Unit,
    onEnterClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(40.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.msg_input_phone_number),
            color = Color.White,
            style = Typography.titleLargeM,
        )

        Spacer(modifier = Modifier.height(20.dp))

        PhoneNumberDisplay(phoneNumber = phoneNumber)

        Spacer(modifier = Modifier.height(20.dp))

        NumberKeypad(
            onNumberClick = onNumberClick,
            onEnterClick = onEnterClick
        )
    }
}
