package com.example.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.PilatesTheme

private val ContentMaxWidth = 960.dp

@Composable
fun PilatesScaffold(
    title: String,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    centerVertically: Boolean = false,
    onBack: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = PilatesTheme.colors.background,
        topBar = { Toolbar(title = title, onBack = onBack) }
    ) { paddingValues ->
        val scroll = if (centerVertically) Modifier else Modifier.verticalScroll(rememberScrollState())

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .then(scroll)
                .padding(horizontal = 32.dp, vertical = 24.dp),
            contentAlignment = if (centerVertically) Alignment.Center else Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = ContentMaxWidth)
                    .fillMaxWidth(),
                content = content
            )
        }

        if (isLoading) {
            Progress()
        }
    }
}

@Composable
private fun PilatesScaffoldSample(isLoading: Boolean = false) {
    PilatesScaffold(
        title = "마일리지 변경",
        isLoading = isLoading,
        onBack = {}
    ) {
        MemberSummary(name = "김하늘", phone = "01012345678")

        Spacer(modifier = Modifier.height(16.dp))

        ValueStepper(
            title = "김하늘님의 마일리지",
            value = 12,
            unit = "점",
            onIncrease = {},
            onDecrease = {}
        )

        Spacer(modifier = Modifier.height(24.dp))

        ConfirmButton(text = "저장", onClick = {})
    }
}
