package com.example.bni.repository

import com.example.bni.data.response.PortfolioDataResponse
import com.example.bni.model.PortfolioModel
import com.example.bni.utils.AppUtils.getJsonString
import com.example.bni.utils.ChartDataAdapterFactory
import com.google.gson.GsonBuilder
import javax.inject.Inject

class PortfolioRepository @Inject constructor(): IPortfolioRepository {

    override fun getPortfolioData(): PortfolioModel {
        val gs = GsonBuilder()
            .registerTypeAdapterFactory(ChartDataAdapterFactory())
            .create()
        val response = gs.fromJson(
            getJsonString("portfolio_response.json"), Array<PortfolioDataResponse>::class.java
        ).toList()
        return PortfolioModel.mapper(response)
    }

}