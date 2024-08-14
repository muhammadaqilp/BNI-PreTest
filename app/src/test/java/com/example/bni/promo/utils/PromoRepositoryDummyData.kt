package com.example.bni.promo.utils

import com.example.bni.network.response.PromoResponse

object PromoRepositoryDummyData {

    fun getPromoResponse(): PromoResponse {
        return UnitTestHelper.getJsonResponse<PromoResponse>("promo_response.json")
    }

    fun getPromoErrorResponse(): PromoResponse {
        return UnitTestHelper.getJsonResponse<PromoResponse>("promo_response_error.json")
    }

}