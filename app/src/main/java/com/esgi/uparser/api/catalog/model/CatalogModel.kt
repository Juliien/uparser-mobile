package com.esgi.uparser.api.catalog.model

import com.google.gson.annotations.SerializedName

data class CatalogModel(
    @SerializedName("userId") val userId: String,
    @SerializedName("codeEncoded") val codeEncoded: String,
    @SerializedName("extensionStart") val extensionStart: String,
    @SerializedName("extensionEnd") val extensionEnd: String,
    @SerializedName("language") val language: String,
    @SerializedName("date") val date: String,
)