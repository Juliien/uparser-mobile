package com.esgi.uparser.api.authentication.model

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("token") val token: String)
