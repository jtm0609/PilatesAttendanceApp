package com.example.designsystem.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.R
import com.example.designsystem.theme.PilatesTheme
import com.example.designsystem.theme.Typography

@Composable
fun LabeledPickerField(
    titleText: String,
    imageVector: ImageVector,
    contentText: String,
    modifier: Modifier = Modifier,
    placeholder: String = stringResource(R.string.text_select_placeholder),
    onClick: () -> Unit
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

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, PilatesTheme.colors.outline, RoundedCornerShape(12.dp))
                .clickable(onClick = onClick),
            shape = RoundedCornerShape(12.dp),
            color = PilatesTheme.colors.surface,
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = contentText.ifBlank { placeholder },
                    color = if (contentText.isBlank()) PilatesTheme.colors.textDisabled else PilatesTheme.colors.textPrimary,
                    style = Typography.bodyLargeR,
                )
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = imageVector,
                    contentDescription = null,
                    tint = PilatesTheme.colors.textSecondary,
                )
            }
        }
    }
}

@Preview(device = Devices.TABLET)
@Composable
private fun LabeledPickerFieldPreview() {
    PilatesTheme {
        Column {
            LabeledPickerField(
                titleText = "이용 기간",
                imageVector = Icons.Filled.List,
                contentText = "",
                onClick = {}
            )
            Spacer(modifier = Modifier.height(20.dp))
            LabeledPickerField(
                titleText = "이용 시작일",
                imageVector = Icons.Filled.DateRange,
                contentText = "2026-11-01",
                onClick = {}
            )
        }
    }
}
