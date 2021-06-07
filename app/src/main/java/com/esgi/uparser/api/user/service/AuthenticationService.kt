package com.esgi.uparser.api.user.service;

import android.content.Context
import android.util.Log
import com.esgi.uparser.api.provider.ApiClient
import com.esgi.uparser.api.user.controller.AuthenticationInterface
import com.esgi.uparser.api.user.model.LoginModel;
import com.google.gson.JsonObject;
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import kotlin.Unit;

class AuthenticationService {
    fun login(loginData:LoginModel, onResult: (JsonObject?) -> Unit) {
        val call:Call<JsonObject> = ApiClient.buildService(AuthenticationInterface::class.java).login(loginData)

        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {
                Log.d("Login", "Success ${response?.body().toString()}")
                onResult(response?.body())
            }
            override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
                Log.d("Login", "Failure ${call?.request()}")
                onResult(null)
            }
        })
    }

    fun testCo(onResult: (JsonObject?) -> Unit) {
        val call:Call<JsonObject> = ApiClient.buildService(AuthenticationInterface::class.java).testCo()

        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {
                Log.d("Login", "Success ${response?.body().toString()}")
                onResult(response?.body())
            }
            override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
                Log.d("Login", "Failure ${call?.request()}")
                onResult(null)
            }
        })
    }
}
