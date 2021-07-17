package com.esgi.uparser.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.esgi.uparser.R
import com.esgi.uparser.api.catalogue.service.CatalogueService
import com.esgi.uparser.api.profile.service.ProfileService

class CatalogueFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.catalogue_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LaunchCatalogue()


    }
    private fun LaunchCatalogue(){
        val catalogueService = CatalogueService()
        catalogueService.getAllCode()
    }

}