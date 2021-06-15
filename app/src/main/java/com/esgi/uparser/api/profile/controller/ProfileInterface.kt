package com.esgi.uparser.api.profile

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface ProfileInterface {
    @Headers("Content-Type: application/json")
    @GET(value = "user")
    fun getInfoUser(@Header("Authorization") token: String): Call<JsonObject>
}