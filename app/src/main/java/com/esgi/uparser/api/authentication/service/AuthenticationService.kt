package com.esgi.uparser.api.authentication.service;

import com.esgi.uparser.api.provider.ApiClient
import com.esgi.uparser.api.authentication.controller.AuthenticationInterface
import com.esgi.uparser.api.authentication.model.LoginModel;
import com.esgi.uparser.api.authentication.model.TokenResponse
import retrofit2.Call
import retrofit2.Callback

class AuthenticationService {

    fun login(loginData: LoginModel,
              callback: Callback<TokenResponse>) {
        val call: Call<TokenResponse> =
            ApiClient.buildService(AuthenticationInterface::class.java).login(loginData)
        call.enqueue(callback)
    }
}
