package com.example.designsystem.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designsystem.theme.PilatesTheme
import com.example.designsystem.theme.Typography

@Composable
fun ValueStepper(
    title: String,
    value: Int,
    unit: String,
    modifier: Modifier = Modifier,
    canDecrease: Boolean = true,
    helper: String? = null,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = PilatesTheme.colors.surface,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                color = PilatesTheme.colors.textSecondary,
                style = Typography.titleSmallM,
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 28.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                StepButton(
                    symbol = "−",
                    enabled = canDecrease,
                    onClick = onDecrease
                )

                Row(
                    modifier = Modifier.padding(horizontal = 40.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = value.toString(),
                        color = PilatesTheme.colors.textPrimary,
                        style = Typography.displayLargeR.copy(fontWeight = FontWeight.SemiBold),
                    )
                    Text(
                        modifier = Modifier.padding(start = 6.dp, bottom = 12.dp),
                        text = unit,
                        color = PilatesTheme.colors.textSecondary,
                        style = Typography.headlineMediumR,
                    )
                }

                StepButton(
                    symbol = "+",
                    enabled = true,
                    onClick = onIncrease
                )
            }

            if (helper != null) {
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = helper,
                    color = PilatesTheme.colors.textDisabled,
                    style = Typography.bodyMediumR,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
private fun StepButton(
    symbol: String,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(80.dp)
            .border(1.dp, if (enabled) PilatesTheme.colors.outline else PilatesTheme.colors.outline.copy(alpha = 0.5f), CircleShape),
        enabled = enabled,
        shape = CircleShape,
        color = if (enabled) PilatesTheme.colors.surfaceRaised else PilatesTheme.colors.surface,
        contentColor = if (enabled) PilatesTheme.colors.primary else PilatesTheme.colors.textDisabled,
        onClick = onClick
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = symbol,
                fontSize = 40.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Preview(device = Devices.TABLET)
@Composable
private fun ValueStepperPreview() {
    ComponentPreview {
        ValueStepper(
            title = "하루 최대 출석 횟수",
            value = 1,
            unit = "회",
            canDecrease = false,
            helper = "한 회원이 같은 날 이 횟수까지 출석할 수 있습니다",
            onIncrease = {},
            onDecrease = {}
        )
    }
}
