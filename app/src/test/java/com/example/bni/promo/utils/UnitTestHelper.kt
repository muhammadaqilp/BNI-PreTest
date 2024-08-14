package com.example.bni.promo.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

object UnitTestHelper {

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