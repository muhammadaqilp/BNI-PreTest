package com.example.bni.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

object AppUtils {

    fun Int.toFormat(): String {
        val nf = NumberFormat.getNumberInstance(Locale.ENGLISH)
        val formatter = nf as DecimalFormat
        formatter.applyPattern("#,###,###,###,###.###")
        return "Rp " + formatter.format(this)
    }

    inline fun <reified T> getJsonResponse(filePath: String): T {
        return Gson().fromJson(
            getJsonString(filePath),
            object : TypeToken<T>() {}.type
        )
    }

    fun getJsonString(filePath: String): String {
        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(filePath))
        val json = reader.readText()
        reader.close()
        return json
    }
}