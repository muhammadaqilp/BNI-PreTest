package com.example.bni.repository

import com.example.bni.model.PromoModel
import io.reactivex.Flowable

interface IPromoRepository {
    fun getPromo(): Flowable<List<PromoModel>>
}