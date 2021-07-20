package com.esgi.uparser.api.catalog.controller

import com.esgi.uparser.api.catalog.model.CatalogModel
import com.esgi.uparser.api.catalog.model.CatalogResponse
import retrofit2.Call
import retrofit2.http.*

interface CatalogInterface {
    @GET("code/all")
    fun getAllCodes(): Call<List<CatalogResponse>>

    @GET("code/{id}")
    fun getCodeById(@Path("id")id : String): Call<CatalogResponse>

    @Headers("Content-Type: application/json")
    @POST("code/quality")
    fun postCode(@Body catalog: CatalogModel): Call<CatalogResponse>

    @GET("code/history/{id}")
    fun getCodeHistory(@Path("id")id : String): Call<List<CatalogResponse>>
}