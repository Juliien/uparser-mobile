package com.esgi.uparser.api.authentication.controller

import com.esgi.uparser.api.authentication.model.LoginModel
import com.esgi.uparser.api.authentication.model.TokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthenticationInterface {
    @Headers("Content-Type: application/json")
    @POST("auth/login")
    fun login(@Body loginData: LoginModel): Call<TokenResponse>
}