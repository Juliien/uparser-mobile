package com.esgi.uparser.api.catalogue.controller;

import com.esgi.uparser.api.catalogue.model.CatalogueModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CatalogueInterface {
    @GET("code/all")
    Call<List<CatalogueModel>> getAllCode();

    //@GET("code/all")
    //fun getAllCode(): Call<List<CatalogueModel>>
}
