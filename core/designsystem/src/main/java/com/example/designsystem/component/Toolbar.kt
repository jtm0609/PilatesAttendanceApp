package com.example.designsystem.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.R
import com.example.designsystem.theme.PilatesTheme
import com.example.designsystem.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    title: String,
    onBack: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = PilatesTheme.colors.background,
            titleContentColor = PilatesTheme.colors.textPrimary,
            navigationIconContentColor = PilatesTheme.colors.textPrimary
        ),
        title = {
            Text(
                text = title,
                color = PilatesTheme.colors.textPrimary,
                style = Typography.headlineSmallM
            )
        },
        navigationIcon = {
            IconButton(
                modifier = Modifier.size(56.dp),
                onClick = onBack
            ) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.cd_back),
                    tint = PilatesTheme.colors.textPrimary
                )
            }
        }
    )
}

@Preview(device = Devices.TABLET)
@Composable
private fun ToolbarPreview() {
    ComponentPreview {
        Toolbar(title = "회원 관리", onBack = {})
    }
}
