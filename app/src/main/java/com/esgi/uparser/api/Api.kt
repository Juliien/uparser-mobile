package com.esgi.uparser.api

import com.esgi.uparser.models.LoginResponse
import com.esgi.uparser.models.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface Api {

    @GET(value = "user/all")
    fun getInfoUser(@Header("Authorization") token: String): Call<List<User>>

    @POST("auth/login")
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ):Call<LoginResponse>
}
