package com.esgi.uparser.api.code.service

import com.esgi.uparser.api.catalog.model.CodeResponse
import com.esgi.uparser.api.code.controller.CodeInterface
import com.esgi.uparser.api.code.model.CodeHistoryResponse
import com.esgi.uparser.api.provider.ApiClient
import retrofit2.Callback

class CodeService {

    fun getCodeHistory(authorization: String,
                       id: String,
                       callback: Callback<List<CodeHistoryResponse>>) {
        val call = ApiClient.buildService(CodeInterface::class.java).getCodeHistory("Bearer $authorization", id);
        call.enqueue(callback)
    }
}