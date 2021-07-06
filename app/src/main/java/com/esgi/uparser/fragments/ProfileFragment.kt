package com.esgi.uparser.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.esgi.uparser.R
import com.esgi.uparser.api.profile.service.ProfileService

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.profile_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LaunchProfile()


    }
    private fun LaunchProfile(){
        val profileService = ProfileService()
        profileService.getInfoUser()
    }

}