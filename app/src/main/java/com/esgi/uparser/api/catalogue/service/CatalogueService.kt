package com.esgi.uparser.api.catalogue.service

import android.util.Log
import com.esgi.uparser.api.catalogue.controller.CatalogueInterface
import com.esgi.uparser.api.catalogue.model.CatalogueModel
import com.esgi.uparser.api.profile.ProfileInterface
import com.esgi.uparser.api.provider.ApiClient
import com.esgi.uparser.api.provider.AppPreferences
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatalogueService {
    fun getAllCode() {
        val call: Call<List<CatalogueModel>> = ApiClient.buildService(CatalogueInterface::class.java).getAllCode()

        call.enqueue(object : Callback<List<CatalogueModel>> {
            override fun onResponse(call: Call<List<CatalogueModel>>?, response: Response<List<CatalogueModel>>?) {
                Log.d("Catalogue", "Success ${response?.body().toString()}")
                val catalogueResponse = response?.body()
                //onResult(response?.body())
            }
            override fun onFailure(call: Call<List<CatalogueModel>>?, t: Throwable?) {
                Log.d("Catalogue", "Failure ${call?.request()}")
                //onResult(null)
            }
        })
    }
}