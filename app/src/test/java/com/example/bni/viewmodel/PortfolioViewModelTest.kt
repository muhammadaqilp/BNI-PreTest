package com.example.bni.viewmodel

import com.example.bni.model.PortfolioModel
import com.example.bni.repository.IPortfolioRepository
import com.example.bni.ui.main.PortfolioViewModel
import com.example.bni.ui.main.screen.PortfolioEvent
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PortfolioViewModelTest {

    private lateinit var viewModel: PortfolioViewModel

    @Mock
    private lateinit var portfolioRepository: IPortfolioRepository

    @Before
    fun setup() {
        viewModel = PortfolioViewModel(portfolioRepository)
    }

    @Test
    fun onEvent_InitializeData_Success() {
        val model = PortfolioModel(
            donutChartData = listOf(
                PortfolioModel.DonutChartData(
                    label = "Top Up Voucher", percentage = 2F, detail = listOf(
                        PortfolioModel.DataDetail(
                            trxDate = "22/3/2024", nominal = 1000
                        )
                    )
                )
            ), lineChartData = listOf(1F, 3F, 4F, 8F)
        )

        `when`(portfolioRepository.getPortfolioData()).thenReturn(model)
        viewModel.onEvent(PortfolioEvent.InitializeData)
        Mockito.verify(portfolioRepository).getPortfolioData()

        assertEquals(model.donutChartData, viewModel.portfolioState.value.donutChartData)
        assertEquals(model.lineChartData, viewModel.portfolioState.value.lineChartData)
        assertNotNull(viewModel.portfolioState.value.donutChartData)
        assertNotNull(viewModel.portfolioState.value.lineChartData)
    }

    @Test
    fun onEvent_ClickDetail_Success() {
        val model = PortfolioModel(
            donutChartData = listOf(
                PortfolioModel.DonutChartData(
                    label = "Top Up Voucher", percentage = 2F, detail = listOf(
                        PortfolioModel.DataDetail(
                            trxDate = "22/3/2024", nominal = 1000
                        )
                    )
                )
            ), lineChartData = listOf(1F, 3F, 4F, 8F)
        )

        val category = "Top Up Voucher"
        val selectedModel = model.donutChartData.find { it.label == category }!!

        `when`(portfolioRepository.getPortfolioData()).thenReturn(model)
        viewModel.onEvent(PortfolioEvent.InitializeData)
        viewModel.onEvent(PortfolioEvent.ClickDetail(category = category))

        assertEquals(selectedModel, viewModel.portfolioDetailState.value.historyData)
        assertNotNull(viewModel.portfolioDetailState.value.historyData)
    }

}