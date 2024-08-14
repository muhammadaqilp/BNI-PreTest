package com.example.bni.ui.main.state

import com.example.bni.model.PortfolioModel

data class PortfolioDetailState(
    val historyData: PortfolioModel.DonutChartData = PortfolioModel.DonutChartData()
)
