package com.example.designsystem.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chargemap.compose.numberpicker.ListItemPicker
import com.example.designsystem.R
import com.example.designsystem.theme.DarkGray
import com.example.designsystem.theme.PalePurple
import com.example.designsystem.theme.LightGray
import com.example.designsystem.theme.Typography
import com.example.designsystem.theme.White

@Composable
fun DurationSettingBox(
    isShow: Boolean,
    onClickDurationEvent: (String) -> Unit,
    onDismissEvent: ()->Unit,
    onValueChange: (String) -> Unit,
    durationState: String,
    durationList: List<String>
) {
    if(isShow) {
        BackHandler {
            onDismissEvent()
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkGray)
                .pointerInput(Unit) {},
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(R.string.msg_guide_selection_duration),
                    color = White,
                    style = Typography.titleSmallM,
                    modifier = Modifier.padding(top = 25.dp)
                )
                Divider(
                    color = LightGray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(top = 25.dp)
                )
            }

            ListItemPicker(
                label = { it },
                value = durationState,
                onValueChange = { onValueChange(it) },
                list = durationList,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(300.dp),
                textStyle = TextStyle(fontSize = 25.sp, color = White),
                dividersColor = PalePurple
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 30.dp, start = 60.dp, end = 60.dp)
                    .clickable {
                        onClickDurationEvent(durationState)
                    },
            ) {
                Text(
                    text = stringResource(R.string.text_setting_button),
                    color = White,
                    style = Typography.labelLargeR,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(
                            LightGray,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .wrapContentSize(align = Alignment.Center)
                )
            }
        }
    }
}