package com.example.attendance.home.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.Gray

@Composable
fun AdminButton(
    modifier: Modifier,
    onAdminClick: () -> Unit,
) {
    IconButton (
        onClick = onAdminClick,
        modifier = modifier.padding(20.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = "admin",
            tint = Gray,
            modifier = Modifier.size(45.dp),
        )
    }
}