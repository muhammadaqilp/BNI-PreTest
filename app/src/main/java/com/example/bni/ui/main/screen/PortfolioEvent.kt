package com.example.bni.ui.main.screen

sealed class PortfolioEvent {
    data object InitializeData : PortfolioEvent()
    data class ClickDetail(
        val category: String
    ) : PortfolioEvent()
}