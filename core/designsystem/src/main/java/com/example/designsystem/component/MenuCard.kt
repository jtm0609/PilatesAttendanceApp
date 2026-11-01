package com.example.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.PilatesTheme
import com.example.designsystem.theme.Typography

@Composable
fun MenuCard(
    title: String,
    description: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        color = PilatesTheme.colors.surface,
        contentColor = PilatesTheme.colors.textPrimary,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(28.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(PilatesTheme.colors.surfaceRaised, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = icon,
                    contentDescription = null,
                    tint = PilatesTheme.colors.primary,
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = title,
                color = PilatesTheme.colors.textPrimary,
                style = Typography.titleMediumM
            )
            Text(
                modifier = Modifier.padding(top = 6.dp),
                text = description,
                color = PilatesTheme.colors.textSecondary,
                style = Typography.bodyMediumR,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    modifier = Modifier.size(22.dp),
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = null,
                    tint = PilatesTheme.colors.textSecondary,
                )
            }
        }
    }
}

@Preview(device = Devices.TABLET)
@Composable
private fun MenuCardPreview() {
    PilatesTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            MenuCard(
                modifier = Modifier.weight(1f),
                title = "신규 회원 등록",
                description = "이름, 전화번호, 이용 기간 입력",
                icon = Icons.Filled.Add,
                onClick = {}
            )
            MenuCard(
                modifier = Modifier.weight(1f),
                title = "회원 관리",
                description = "전화번호로 찾아 정보 수정",
                icon = Icons.Filled.Person,
                onClick = {}
            )
        }
    }
}
