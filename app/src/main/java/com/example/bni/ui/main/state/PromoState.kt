package com.example.bni.ui.main.state

import com.example.bni.model.PromoModel

data class PromoState(
    val promoData: List<PromoModel> = listOf(),
    val errorMessage: String = "",
    val type: Type = Type.LOADING
) {
    enum class Type {
        LOADING, SUCCESS, ERROR
    }
}
