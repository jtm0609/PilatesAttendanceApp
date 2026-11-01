package com.example.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.PilatesTheme
import com.example.designsystem.theme.Typography

@Composable
fun ConfirmButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PilatesTheme.colors.primary,
            contentColor = PilatesTheme.colors.onPrimary,
            disabledContainerColor = PilatesTheme.colors.surfaceRaised,
            disabledContentColor = PilatesTheme.colors.textDisabled,
        ),
        onClick = onClick
    ) {
        Text(
            text = text,
            style = Typography.labelLargeR
        )
    }
}

@Preview(device = Devices.TABLET)
@Composable
private fun ConfirmButtonPreview() {
    PilatesTheme {
        Column {
            ConfirmButton(text = "저장", onClick = {})
            Spacer(modifier = Modifier.height(16.dp))
            ConfirmButton(text = "저장", enabled = false, onClick = {})
        }
    }
}
