package com.esgi.uparser.api.profile.service

import android.util.Log
import com.esgi.uparser.api.profile.ProfileInterface
import com.esgi.uparser.api.provider.ApiClient
import com.esgi.uparser.api.provider.AppPreferences
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileService {
    fun getInfoUser() {
        val call: Call<JsonObject> = ApiClient.buildService(ProfileInterface::class.java).getInfoUser(AppPreferences.email)

        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {
                Log.d("Profile", "Success ${response?.body().toString()}")
                //onResult(response?.body())
            }
            override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
                Log.d("Profile", "Failure ${call?.request()}")
                //onResult(null)
            }
        })
    }
}