package com.example.bni.utils

import com.example.bni.data.response.PortfolioDataResponse
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.JsonParser
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

class ChartDataAdapterFactory : TypeAdapterFactory {
    override fun <T : Any?> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T>? {
        if (type.rawType != PortfolioDataResponse::class.java) {
            return null
        }
        return ChartDataAdapter(gson) as TypeAdapter<T>
    }
}

class ChartDataAdapter(private val gson: Gson) : TypeAdapter<PortfolioDataResponse>() {
    override fun write(out: JsonWriter, value: PortfolioDataResponse?) {
        if (value == null) {
            out.nullValue()
            return
        }

        out.beginObject()
        out.name("type")
        out.value(value.type)

        when (value.type) {
            "donutChart" -> {
                out.name("data")
                out.beginArray()
                value.data?.forEach { chartDataItem ->
                    out.beginObject()
                    out.name("label")
                    out.value(chartDataItem.label)
                    out.name("percentage")
                    out.value(chartDataItem.percentage)
                    out.name("data")
                    out.beginArray()
                    chartDataItem.data?.forEach { transactionData ->
                        out.beginObject()
                        out.name("trx_date")
                        out.value(transactionData.trx_date)
                        out.name("nominal")
                        out.value(transactionData.nominal)
                        out.endObject()
                    }
                    out.endArray()
                    out.endObject()
                }
                out.endArray()
            }

            "lineChart" -> {
                out.name("data")
                out.beginObject()
                out.name("month")
                out.beginArray()
                value.lineChartData?.month?.forEach { month ->
                    out.value(month)
                }
                out.endArray()
                out.endObject()
            }
        }

        out.endObject()
    }

    override fun read(`in`: JsonReader): PortfolioDataResponse {
        val jsonObject = JsonParser.parseReader(`in`).asJsonObject

        val type = jsonObject.get("type").asString

        val portfolioDataResponse = when (type) {
            "donutChart" -> {
                val data =
                    gson.fromJson(jsonObject.get("data"), Array<PortfolioDataResponse.ChartDataItem>::class.java).toList()
                PortfolioDataResponse(type, data, null)
            }

            "lineChart" -> {
                val lineChartData =
                    gson.fromJson(jsonObject.get("data"), PortfolioDataResponse.LineChartData::class.java)
                PortfolioDataResponse(type, null, lineChartData)
            }

            else -> throw JsonParseException("Unknown chart type: $type")
        }

        return portfolioDataResponse
    }
}