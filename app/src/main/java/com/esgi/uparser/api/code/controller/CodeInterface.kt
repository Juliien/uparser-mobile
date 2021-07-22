package com.esgi.uparser.api.code.controller

import com.esgi.uparser.api.catalog.model.CodeResponse
import retrofit2.Call
import retrofit2.http.*

interface CodeInterface {
    @GET("code/history/{id}")
    fun getCodeHistory(
        @Header("Authorization") authorization: String,
        @Path("id")id : String
    ): Call<List<CodeResponse>>
}