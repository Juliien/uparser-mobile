package com.esgi.uparser.api.session.service

import android.content.Context
import android.util.Log
import com.esgi.uparser.LoginActivity
import com.esgi.uparser.api.profile.service.ProfileService
import com.esgi.uparser.api.provider.AppPreferences
import com.esgi.uparser.api.token.JWTUtils
import com.esgi.uparser.api.profile.model.UserResponse
import com.esgi.uparser.api.token.TokenResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SessionService {

    fun deleteToken() {
        AppPreferences.clear("token")
    }
    fun deleteEmail() {
        AppPreferences.clear("email")
    }

    fun deleteId() {
        AppPreferences.clear("id")
    }
    fun setIsLoginToFalse() {
        AppPreferences.isLogin = false
    }

    fun retrieveUserInfoViaToken(token: String): TokenResponse {
        val tokenResponse = JWTUtils.decoded(token);
        return tokenResponse;
    }

    fun testUserToken(userToken: String): Boolean {
        val tokenResponse: TokenResponse = retrieveUserInfoViaToken(userToken);

        var isValid: Boolean = false

        if (tokenResponse.userId == "" || tokenResponse.email == "") {
            deleteToken()
            deleteEmail()
            deleteId()
            setIsLoginToFalse()

            return isValid
        } else {
            val email = tokenResponse.email
            val profileService = ProfileService();

            profileService.getUserByEmail(userToken, email, object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    if (response.body() == null){
                        deleteToken()
                        isValid = false
                    } else {
                        AppPreferences.token = userToken
                        AppPreferences.email = response.body()?.email.toString()
                        AppPreferences.id = response.body()?.id.toString()
                        AppPreferences.isLogin = true
                        isValid = true
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    deleteToken()
                    isValid = false
                }
            })
        }
        return isValid;
    }

    fun disconnect(context: Context) {
        deleteToken()
        LoginActivity.navigateTo(context)
    }
}