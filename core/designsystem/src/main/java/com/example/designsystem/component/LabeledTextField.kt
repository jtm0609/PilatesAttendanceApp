package com.example.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.PilatesTheme
import com.example.designsystem.theme.Typography

@Composable
fun LabeledTextField(
    modifier: Modifier = Modifier,
    titleText: String,
    hintText: String,
    keyboardType: KeyboardType,
    content: String,
    enabled: Boolean,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = titleText,
            color = PilatesTheme.colors.textSecondary,
            style = Typography.labelMediumR
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = content,
            enabled = enabled,
            singleLine = true,
            textStyle = Typography.bodyLargeR.copy(color = PilatesTheme.colors.textPrimary),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            ),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = PilatesTheme.colors.surface,
                unfocusedContainerColor = PilatesTheme.colors.surface,
                disabledContainerColor = PilatesTheme.colors.surface
            ),
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = hintText,
                    color = PilatesTheme.colors.textDisabled,
                    style = Typography.bodyLargeR
                )
            }
        )
    }
}

@Preview(device = Devices.TABLET)
@Composable
private fun LabeledTextFieldPreview() {
    ComponentPreview {
        Column {
            LabeledTextField(
                titleText = "성명",
                hintText = "홍길동",
                keyboardType = KeyboardType.Text,
                content = "",
                enabled = true,
                onValueChange = {}
            )
            Spacer(modifier = Modifier.height(20.dp))
            LabeledTextField(
                titleText = "전화번호",
                hintText = "01012345678",
                keyboardType = KeyboardType.Number,
                content = "01012345678",
                enabled = true,
                onValueChange = {}
            )
        }
    }
}
