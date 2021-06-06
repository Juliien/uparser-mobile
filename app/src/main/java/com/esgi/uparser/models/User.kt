package com.esgi.uparser.models

import java.time.LocalDate
import java.util.*

data class User(val id:String,
                val firstName:String,
                val lastName:String,
                val email:String,
                val password:String,
                val createDate:LocalDate,
                val Date:Date,
                val lastLoginDate:Date
                )
