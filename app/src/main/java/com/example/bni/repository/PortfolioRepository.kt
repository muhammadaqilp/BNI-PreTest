package com.example.bni.repository

import com.example.bni.data.response.PortfolioDataResponse
import com.example.bni.model.PortfolioModel
import com.example.bni.utils.AppUtils.getJsonString
import com.google.gson.Gson
import javax.inject.Inject

class PortfolioRepository @Inject constructor(
    private val gson: Gson
) : IPortfolioRepository {

    override fun getPortfolioData(): PortfolioModel {
        val response = gson.fromJson(
            getJsonString("portfolio_response.json"), Array<PortfolioDataResponse>::class.java
        ).toList()
        return PortfolioModel.mapper(response)
    }

}