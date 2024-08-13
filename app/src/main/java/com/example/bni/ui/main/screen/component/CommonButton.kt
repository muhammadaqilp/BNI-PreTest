package com.example.bni.ui.main.screen.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CommonButton(title: String, onClicked: () -> Unit) {
    Button(onClick = onClicked, modifier = Modifier.padding(8.dp)) {
        Text(text = title)
    }
}