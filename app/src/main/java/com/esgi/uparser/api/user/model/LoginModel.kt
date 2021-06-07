package com.esgi.uparser.api.user.model

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

data class LoginModel(
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("password")
    val password: String,
)
