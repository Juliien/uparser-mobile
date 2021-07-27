package com.esgi.uparser.api.code.model

import com.google.gson.annotations.SerializedName

open class CodeHistoryResponse (
    @SerializedName("id") val id: String,
    @SerializedName("userId") val userId: String,
    @SerializedName("codeEncoded") val codeEncoded: String,
    @SerializedName("language") val language: String,
    @SerializedName("date") val date: String
)