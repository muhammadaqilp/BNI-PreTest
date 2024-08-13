package com.example.bni.utils

import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

inline fun <reified T : Any> serializableType(
    isNullableAllowed: Boolean = false,
    json: Json = Json,
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String) =
        bundle.getString(key)?.let<String, T>(json::decodeFromString)

    override fun parseValue(value: String): T = json.decodeFromString(value)
    override fun serializeAsValue(value: T): String = json.encodeToString(value)

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, json.encodeToString(value))
    }
}

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