package com.esgi.uparser.api.profile.model

import java.time.LocalDate
import java.util.*

data class UserModel(
    val id:String,
    val firstName:String,
    val lastName:String,
    val email:String,
    val password:String,
    val createDate: String,
    val Date: String,
    val lastLoginDate: String
)