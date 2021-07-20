package com.esgi.uparser.api.catalog.service

import com.esgi.uparser.api.catalog.controller.CatalogInterface
import com.esgi.uparser.api.catalog.model.CatalogModel
import com.esgi.uparser.api.catalog.model.CatalogResponse
import com.esgi.uparser.api.provider.ApiClient
import retrofit2.Callback

class CatalogService {

    fun getAllCodes(callback: Callback<List<CatalogResponse>>) {
        val call = ApiClient.buildService(CatalogInterface::class.java).getAllCodes();
        call.enqueue(callback);
    }

    fun getCodeById(id : String, callback: Callback<CatalogResponse>) {
        val call = ApiClient.buildService(CatalogInterface::class.java).getCodeById(id);
        call.enqueue(callback);
    }

    fun postCode(catalog: CatalogModel, callback: Callback<CatalogResponse>) {
        val call = ApiClient.buildService(CatalogInterface::class.java).postCode(catalog);
        call.enqueue(callback)
    }

    fun getCodeHistory(id: String, callback: Callback<List<CatalogResponse>>) {
        val call = ApiClient.buildService(CatalogInterface::class.java).getCodeHistory(id);
        call.enqueue(callback)
    }
}