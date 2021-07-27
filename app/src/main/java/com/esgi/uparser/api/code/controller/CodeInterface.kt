package com.esgi.uparser.api.code.controller

import com.esgi.uparser.api.catalog.model.CodeResponse
import com.esgi.uparser.api.code.model.CodeHistoryResponse
import retrofit2.Call
import retrofit2.http.*

interface CodeInterface {
    @GET("history/user/{id}")
    fun getCodeHistory(
        @Header("Authorization") authorization: String,
        @Path("id")id : String
    ): Call<List<CodeHistoryResponse>>
}