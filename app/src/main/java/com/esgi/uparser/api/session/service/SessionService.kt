package com.esgi.uparser.api.session.service

import android.content.Context
import com.esgi.uparser.LoginActivity
import com.esgi.uparser.api.profile.service.ProfileService
import com.esgi.uparser.api.provider.AppPreferences
import com.esgi.uparser.api.token.JWTUtils
import com.esgi.uparser.api.profile.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SessionService {

    fun deleteToken() {
        AppPreferences.clear("token")
    }

    fun testUserToken(userToken: String): Boolean {
        var isValid: Boolean = false
        val tokenResponse = JWTUtils.decoded(userToken);
        if (tokenResponse.userId == "" || tokenResponse.email == "") {
            deleteToken()
            return isValid
        }
        val email = tokenResponse.email
        val profileService = ProfileService();

        profileService.getUserByEmail(userToken, email, object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.body() == null){
                    deleteToken()
                    isValid = false
                } else {
                    isValid = true
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                deleteToken()
                isValid = false
            }
        })
        return isValid;
    }

    fun disconnect(context: Context) {
        deleteToken()
        LoginActivity.navigateTo(context)
    }
}