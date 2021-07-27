package com.esgi.uparser.api.catalog.model

import com.google.gson.annotations.SerializedName

open class CodeResponse (
    @SerializedName("id") val id: String,
    @SerializedName("userId") val userId: String,
    @SerializedName("codeEncoded") val codeEncoded: String,
    @SerializedName("hash") val hash: String,
    @SerializedName("extensionStart") val extensionStart: String,
    @SerializedName("extensionEnd") val extensionEnd: String,
    @SerializedName("language") val language: String,
    @SerializedName("codeMark") val codeMark: Int,
    @SerializedName("isEnable") val isEnable: Boolean,
//    @SerializedName("isValid") val isValid: Boolean,
    @SerializedName("date") val date: String
)