package com.example.bni.ui.main.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bni.ui.main.screen.component.PortfolioDonutChart
import com.example.bni.ui.main.screen.component.PortfolioLineChart
import com.example.bni.ui.main.state.PortfolioState

@Composable
fun PortfolioChartScreen(
    state: PortfolioState = PortfolioState(),
    onClickDetail: (String) -> Unit
) {

    var chartType by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SpinnerChart(onSelectionChanged = {
            chartType = it
        })
        if (chartType == 0) {
            PortfolioDonutChart(data = state.donutChartData.map {
                Pair(
                    it.label,
                    it.percentage
                )
            }, onClick = {
                onClickDetail(it)
            })
        } else {
            PortfolioLineChart(values = state.lineChartData)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PortfolioChartScreenPreview() {
    PortfolioChartScreen(onClickDetail = {})
}