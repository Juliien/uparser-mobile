package com.esgi.uparser.api.profile.model

import com.google.gson.annotations.SerializedName

open class UserResponse (
    @SerializedName("id") val id: String,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("createDate") val createDate: String,
    @SerializedName("closeDate") val closeDate: String,
    @SerializedName("lastLoginDate") val lastLoginDate: String
)