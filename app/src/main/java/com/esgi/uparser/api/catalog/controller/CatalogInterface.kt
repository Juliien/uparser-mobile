package com.esgi.uparser.api.catalog.controller

import com.esgi.uparser.api.catalog.model.CodeResponse
import retrofit2.Call
import retrofit2.http.*

interface CatalogInterface {
    @GET("catalog")
    fun getAllCodes(): Call<List<CodeResponse>>

    @GET("catalog/item/{id}")
    fun getCodeById(@Path("id")id : String): Call<CodeResponse>
}