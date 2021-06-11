package com.esgi.uparser.api.user.model

import java.time.LocalDate
import java.util.*

data class UserModel(
    val id:String,
    val firstName:String,
    val lastName:String,
    val email:String,
    val password:String,
    val createDate:LocalDate,
    val Date:Date,
    val lastLoginDate:Date
)
