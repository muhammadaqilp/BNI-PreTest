package com.example.bni.ui.main.screen.component

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
import com.example.bni.model.PortfolioModel
import com.example.bni.utils.AppUtils.toFormat

@Composable
fun HistoryItem(state: PortfolioModel.DataDetail) {
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
                Text(text = state.trxDate)
                Text(
                    text = state.nominal.toFormat(),
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
        state = PortfolioModel.DataDetail(
            trxDate = "22/11/2023",
            nominal = 550000
        )
    )
}