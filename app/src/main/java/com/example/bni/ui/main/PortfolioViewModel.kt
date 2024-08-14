package com.example.bni.ui.main

import androidx.lifecycle.ViewModel
import com.example.bni.repository.IPortfolioRepository
import com.example.bni.ui.main.screen.PortfolioEvent
import com.example.bni.ui.main.state.PortfolioDetailState
import com.example.bni.ui.main.state.PortfolioState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val portfolioRepository: IPortfolioRepository
) : ViewModel() {

    private val _portfolioState: MutableStateFlow<PortfolioState> =
        MutableStateFlow(PortfolioState())
    val portfolioState: StateFlow<PortfolioState> = _portfolioState

    private val _portfolioDetailState: MutableStateFlow<PortfolioDetailState> =
        MutableStateFlow(PortfolioDetailState())
    val portfolioDetailState: StateFlow<PortfolioDetailState> = _portfolioDetailState

    fun onEvent(event: PortfolioEvent) {
        when (event) {
            is PortfolioEvent.InitializeData -> {
                getPortfolioData()
            }

            is PortfolioEvent.ClickDetail -> {
                click(event.category)
            }
        }
    }

    private fun getPortfolioData() {
        portfolioRepository.getPortfolioData().let { data ->
            _portfolioState.update {
                PortfolioState(
                    donutChartData = data.donutChartData,
                    lineChartData = data.lineChartData
                )
            }
        }
    }

    private fun click(category: String) {
        _portfolioDetailState.update {
            val a = _portfolioState.value.donutChartData.find { it.label == category }!!
            PortfolioDetailState(a)
        }
    }

}