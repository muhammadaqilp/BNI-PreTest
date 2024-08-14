package com.example.bni.repository

import com.example.bni.model.PromoModel
import com.example.bni.network.service.PromoApi
import io.reactivex.Flowable
import javax.inject.Inject

class PromoRepository @Inject constructor(
    private val promoApi: PromoApi
) : IPromoRepository {
    override fun getPromo(): Flowable<List<PromoModel>> {
        return promoApi.getListPromo().map {
            if (it.data != null) {
                it
            } else {
                throw Exception(it.error?.message)
            }
        }.map(PromoModel.Mapper())
    }
}