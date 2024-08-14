package com.example.bni.ui.main.state

import com.example.bni.model.PortfolioModel

data class PortfolioState(
    val donutChartData: List<PortfolioModel.DonutChartData> = listOf(),
    val lineChartData: List<Float> = listOf()
)
