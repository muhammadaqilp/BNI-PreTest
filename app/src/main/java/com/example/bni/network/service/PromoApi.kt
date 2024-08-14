package com.example.bni.network.service

import com.example.bni.network.response.PromoResponse
import io.reactivex.Flowable
import retrofit2.http.GET

interface PromoApi {

    @GET("api/promos")
    fun getListPromo(): Flowable<PromoResponse>

}