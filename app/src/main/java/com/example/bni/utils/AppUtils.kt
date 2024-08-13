package com.example.bni.utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun Double.toFormat(): String {
    val nf = NumberFormat.getNumberInstance(Locale.ENGLISH)
    val formatter = nf as DecimalFormat
    formatter.applyPattern("#,###,###,###,###.###")
    return "Rp " + formatter.format(this)
}

fun String.toFormat(): String {
    val nf = NumberFormat.getNumberInstance(Locale.ENGLISH)
    val formatter = nf as DecimalFormat
    formatter.applyPattern("#,###,###,###,###.###")
    return "Rp " + formatter.format(this.toDouble())
}