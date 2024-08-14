package com.example.bni.model

import com.example.bni.data.response.PortfolioDataResponse
import kotlinx.serialization.Serializable

@Serializable
data class PortfolioModel(
    val donutChartData: List<DonutChartData>,
    val lineChartData: List<Float>
) {

    @Serializable
    data class DonutChartData(
        val label: String = "",
        val percentage: Float = 0F,
        val detail: List<DataDetail> = listOf()
    )

    @Serializable
    data class DataDetail(
        val trxDate: String = "",
        val nominal: Int = 0
    )

    companion object {
        fun mapper(response: List<PortfolioDataResponse>): PortfolioModel {
            return PortfolioModel(
                donutChartData = response.find { it.type == DONUT_CHART }?.data?.map {
                    DonutChartData(
                        label = it.label,
                        percentage = it.percentage.toFloat(),
                        detail = it.data?.map { it3 ->
                            DataDetail(
                                trxDate = it3.trx_date,
                                nominal = it3.nominal
                            )
                        } ?: emptyList()
                    )
                }.orEmpty(),
                lineChartData = response.find { it.type == LINE_CHART }?.lineChartData?.month?.map {
                    it.toFloat()
                }.orEmpty()
            )
        }

        private const val DONUT_CHART = "donutChart"
        private const val LINE_CHART = "lineChart"
    }

}