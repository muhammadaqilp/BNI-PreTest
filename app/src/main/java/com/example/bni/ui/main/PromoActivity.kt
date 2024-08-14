package com.example.bni.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.bni.ui.theme.BniTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PromoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            BniTheme {
                PromoApp()
            }
        }
    }
}