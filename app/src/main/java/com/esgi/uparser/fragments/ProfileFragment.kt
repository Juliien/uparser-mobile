package com.esgi.uparser.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.esgi.uparser.MainActivity
import com.esgi.uparser.R
import com.esgi.uparser.api.profile.service.ProfileService
import com.esgi.uparser.api.provider.AppPreferences
import com.esgi.uparser.api.user.model.LoginModel
import com.esgi.uparser.api.user.model.UserModel
import com.esgi.uparser.api.user.service.AuthenticationService
import com.google.gson.JsonObject

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.profile_fragment, container, false)

        val profileService = ProfileService()

        if (AppPreferences.isLogin)
        {
            profileService.getInfoUser()

        }

    }



}