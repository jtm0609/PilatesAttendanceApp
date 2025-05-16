package com.example.attendance.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.designsystem.component.ConfirmButton

@Composable
fun NumberKeypad(
    onNumberClick: (String) -> Unit,
    onEnterClick: (String) -> Unit
) {
    val numberRows = listOf(
        listOf("7", "8", "9"),
        listOf("4", "5", "6"),
        listOf("1", "2", "3"),
        listOf("←", "0", "")
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        numberRows.forEach { rowNumbers ->
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                rowNumbers.forEach { number ->
                    NumberKey(
                        number = number,
                        onClick = { onNumberClick(number) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        ConfirmButton(
            modifier = Modifier.padding(vertical =  20.dp),
            text = "입장",
            onClick = { onEnterClick }
        )
    }
}