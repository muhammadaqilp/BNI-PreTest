package com.example.bni.network.response

import com.google.gson.annotations.SerializedName

data class PromoResponse(
    @SerializedName("data")
    val data: List<Data?>?,
    @SerializedName("meta")
    val meta: Meta?,
    @SerializedName("error")
    val error: Error?
) {

    data class Data(
        @SerializedName("attributes")
        val attributes: Attributes?,
        @SerializedName("id")
        val id: Int?
    )

    data class Attributes(
        @SerializedName("alt")
        val alt: Int?,
        @SerializedName("count")
        val count: Int?,
        @SerializedName("createdAt")
        val createdAt: String?,
        @SerializedName("desc")
        val desc: String?,
        @SerializedName("desc_promo")
        val descPromo: String?,
        @SerializedName("latitude")
        val latitude: String?,
        @SerializedName("lokasi")
        val lokasi: String?,
        @SerializedName("longitude")
        val longitude: String?,
        @SerializedName("nama")
        val nama: String?,
        @SerializedName("name_promo")
        val namePromo: String?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("updatedAt")
        val updatedAt: String?
    )

    data class Meta(
        @SerializedName("pagination")
        val pagination: Pagination?
    )

    data class Pagination(
        @SerializedName("page")
        val page: Int?,
        @SerializedName("pageCount")
        val pageCount: Int?,
        @SerializedName("pageSize")
        val pageSize: Int?,
        @SerializedName("total")
        val total: Int?
    )

    data class Error(
        @SerializedName("message")
        val message: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("status")
        val status: Int?
    )

}