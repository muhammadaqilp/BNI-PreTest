package com.example.bni.ui.main.navigation

import kotlinx.serialization.Serializable

@Serializable
object Dashboard

@Serializable
object ScanQr

@Serializable
data class PaymentDetail(
    val qrString: String
)

@Serializable
object PaymentSuccessful

@Serializable
object History