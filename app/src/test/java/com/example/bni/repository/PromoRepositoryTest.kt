package com.example.bni.repository

import com.example.bni.model.PortfolioModel
import com.example.bni.utils.UnitTestHelper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PromoRepositoryTest {

    private lateinit var portfolioRepository: IPortfolioRepository

    @Before
    fun setup() {
        portfolioRepository = PortfolioRepository()
    }

    @Test
    fun getPortfolioData_SuccessGetPortfolioData_GiveValidResponse() {
        val dummyResponse = UnitTestHelper.getDummyResponse()
        val mappedResponse = PortfolioModel.mapper(dummyResponse)

        val response = portfolioRepository.getPortfolioData()
        assertEquals(mappedResponse, response)
        assertEquals(mappedResponse.donutChartData, response.donutChartData)
        assertEquals(mappedResponse.lineChartData, response.lineChartData)
    }

}