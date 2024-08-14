package com.example.bni.model

import com.example.bni.network.response.PromoResponse
import io.reactivex.functions.Function
import kotlinx.serialization.Serializable

@Serializable
data class PromoModel(
    val title: String,
    val desc: String,
    val descPromo: String,
    val location: String,
    val promoName: String,
    val promoCount: Int
) {

    class Mapper : Function<PromoResponse, List<PromoModel>> {
        override fun apply(t: PromoResponse): List<PromoModel> {
            return t.data?.map {
                PromoModel(
                    title = it?.attributes?.title.orEmpty(),
                    desc = it?.attributes?.desc.orEmpty(),
                    descPromo = it?.attributes?.descPromo.orEmpty(),
                    location = it?.attributes?.lokasi.orEmpty(),
                    promoName = it?.attributes?.namePromo.orEmpty(),
                    promoCount = it?.attributes?.count ?: 0
                )
            } ?: listOf()
        }

    }

}
