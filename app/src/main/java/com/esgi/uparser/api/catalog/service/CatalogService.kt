package com.esgi.uparser.api.catalog.service

import com.esgi.uparser.api.catalog.controller.CatalogInterface
import com.esgi.uparser.api.catalog.model.CodeResponse
import com.esgi.uparser.api.provider.ApiClient
import retrofit2.Callback

class CatalogService {

    fun getAllCodes(callback: Callback<List<CodeResponse>>) {
        val call = ApiClient.buildService(CatalogInterface::class.java).getAllCodes();
        call.enqueue(callback);
    }

    fun getCodeById(id : String, callback: Callback<CodeResponse>) {
        val call = ApiClient.buildService(CatalogInterface::class.java).getCodeById(id);
        call.enqueue(callback);
    }
}