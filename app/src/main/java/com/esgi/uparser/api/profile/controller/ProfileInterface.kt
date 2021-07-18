package com.esgi.uparser.api.profile

import android.provider.ContactsContract
import com.esgi.uparser.api.provider.AppPreferences
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface ProfileInterface {
    @Headers("Content-Type: application/json")
    @GET(value = "user")
    fun getInfoUser(@Header("Authorization") authorization: String, @Query(encoded = true,value = "email") email:String) :Call<JsonObject>
}