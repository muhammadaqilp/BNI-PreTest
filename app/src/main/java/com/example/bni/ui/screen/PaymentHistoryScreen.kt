package com.example.bni.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.bni.R
import com.example.bni.ui.screen.component.HistoryItem
import com.example.bni.ui.screen.component.TopAppBar
import com.example.bni.ui.state.HistoryTransactionState

@Composable
fun PaymentHistoryScreen(state: List<HistoryTransactionState> = listOf(), onFinish: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = stringResource(id = R.string.trx_history)) {
                onFinish()
            }
        }
    ) { innerPadding ->
        if (state.isEmpty()) {
            Text(
                text = stringResource(id = R.string.empty_history),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(state.reversed()) {
                    HistoryItem(state = it)
                }
            }
        }
    }
}

@Preview
@Composable
private fun PaymentHistoryScreenPreview() {
    val list = mutableListOf<HistoryTransactionState>()
    for (i in 0..2) {
        list.add(
            HistoryTransactionState(
                merchantName = "Toko Berkah",
                transactionAmount = 55000.0
            )
        )
    }
    PaymentHistoryScreen(state = list, onFinish = {})
}