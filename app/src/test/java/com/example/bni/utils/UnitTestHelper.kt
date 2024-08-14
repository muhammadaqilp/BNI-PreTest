package com.example.bni.utils

import com.example.bni.data.response.PortfolioDataResponse
import com.example.bni.utils.AppUtils.getJsonString
import com.google.gson.GsonBuilder

object UnitTestHelper {

    fun getDummyResponse(): List<PortfolioDataResponse> {
        val gson = GsonBuilder()
            .registerTypeAdapterFactory(ChartDataAdapterFactory())
            .create()
        return gson.fromJson(
            getJsonString("portfolio_response.json"), Array<PortfolioDataResponse>::class.java
        ).toList()
    }

}