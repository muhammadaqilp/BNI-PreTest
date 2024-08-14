package com.example.bni.ui.main.screen.event

sealed class PromoEvent {
    data object GetPromo: PromoEvent()
    data object RetryGetPromo: PromoEvent()
}