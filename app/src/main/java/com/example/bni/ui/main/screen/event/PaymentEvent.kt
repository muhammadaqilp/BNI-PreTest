package com.example.bni.ui.main.screen.event

sealed class PaymentEvent {
    data object InitializeData : PaymentEvent()

    data class OnClickPayment(
        val merchantName: String,
        val transactionAmount: Double
    ) : PaymentEvent()

    data object ResetPaymentState : PaymentEvent()
}