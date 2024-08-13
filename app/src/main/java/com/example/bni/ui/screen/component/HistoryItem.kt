package com.example.bni.ui.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bni.ui.state.HistoryTransactionState
import com.example.bni.utils.toFormat

@Composable
fun HistoryItem(state: HistoryTransactionState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f)
            ) {
                Text(text = state.merchantName)
                Text(
                    text = state.transactionAmount.toFormat(),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            Image(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Icon Shopping",
                modifier = Modifier.weight(1f)
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .padding(top = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HistoryItemComponent() {
    HistoryItem(
        state = HistoryTransactionState(
            merchantName = "Toko Amal Ibadah",
            transactionAmount = 550000.0
        )
    )
}