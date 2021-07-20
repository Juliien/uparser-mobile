package com.esgi.uparser.api.profile.controller

import com.esgi.uparser.api.profile.model.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ProfileInterface {

    @GET("user")
    fun getUserByEmail(
        @Header("Authorization") authorization: String,
        @Query("email") email: String
    ): Call<UserResponse>

}