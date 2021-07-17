package com.esgi.uparser.api.catalogue.model

import java.util.*

data class CatalogueModel(
    val id:String,
    val userId:String,
    val codeEncoded:String,
    val extensionStart:String,
    val extensionEnd:String,
    val language: String,
    val Date: Date
)
