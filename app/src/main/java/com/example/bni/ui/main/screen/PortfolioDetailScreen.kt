package com.example.bni.ui.main.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bni.R
import com.example.bni.model.PortfolioModel
import com.example.bni.ui.main.screen.component.HistoryItem
import com.example.bni.ui.main.state.PortfolioDetailState

@Composable
fun PortfolioDetailScreen(state: PortfolioDetailState) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = stringResource(id = R.string.transaction_history_title, state.historyData.label),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
            items(state.historyData.detail) {
                HistoryItem(state = it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PortfolioDetailScreenPreview() {
    val list = mutableListOf<PortfolioModel.DataDetail>()
    for (i in 0..2) {
        list.add(
            PortfolioModel.DataDetail(
                trxDate = "12/08/2024",
                nominal = 50000
            )
        )
    }
    PortfolioDetailScreen(
        state = PortfolioDetailState(
            PortfolioModel.DonutChartData(
                label = "Top Up Gopay",
                percentage = 0F,
                detail = list
            )
        )
    )
}