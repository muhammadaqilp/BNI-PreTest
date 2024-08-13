package com.example.bni.ui.screen.component

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String,
    onFinish: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            navigationIconContentColor = Color.Unspecified,
        ),
        navigationIcon = {
            IconButton(onClick = {
                onFinish()
            }) {
                Image(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Arrow Back"
                )
            }
        })
}