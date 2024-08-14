package com.example.bni.ui.main.navigation

import com.example.bni.model.PromoModel
import com.example.bni.util.serializableType
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
object PromoList

@Serializable
data class PromoDetail(
    val data: PromoModel
) {
    companion object {
        val typeMap = mapOf(typeOf<PromoModel>() to serializableType<PromoModel>())
    }
}