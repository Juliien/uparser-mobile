package com.esgi.uparser.api.profile.service

import com.esgi.uparser.api.profile.controller.ProfileInterface
import com.esgi.uparser.api.provider.ApiClient
import com.esgi.uparser.api.profile.model.UserResponse
import retrofit2.Callback

class ProfileService {
    fun getUserByEmail(
        authorization: String,
        email: String,
        callback: Callback<UserResponse>
    ) {
        val call =
            ApiClient.buildService(ProfileInterface::class.java).getUserByEmail("Bearer $authorization", email);
        call.enqueue(callback);
    }
}