package com.esgi.uparser.api.session.service

import com.esgi.uparser.api.profile.service.ProfileService
import com.esgi.uparser.api.provider.AppPreferences
import com.esgi.uparser.api.token.JWTUtils
import com.esgi.uparser.api.profile.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SessionService {

    fun deleteInvalidToken() {
        AppPreferences.clear("token")
    }

    fun testUserToken(userToken: String) {
        val tokenResponse = JWTUtils.decoded(userToken);
        if (tokenResponse.userId == "" || tokenResponse.email == "") {
            deleteInvalidToken()
            return
        }
        val email = tokenResponse.email
        val profileService = ProfileService();

        profileService.getUserByEmail(userToken, email, object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.body() == null){
                    deleteInvalidToken()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                deleteInvalidToken()
            }
        })
    }
}