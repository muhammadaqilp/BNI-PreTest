package com.example.bni.data.response

import kotlinx.serialization.Serializable

@Serializable
data class PortfolioDataResponse(
    val type: String,
    val data: List<ChartDataItem>? = null,
    val lineChartData: LineChartData? = null
) {

    @Serializable
    data class ChartDataItem(
        val label: String,
        val percentage: String,
        val data: List<TransactionData>?
    )

    @Serializable
    data class TransactionData(
        val trx_date: String,
        val nominal: Int
    )

    @Serializable
    data class LineChartData(
        val month: List<Int>
    )

}
