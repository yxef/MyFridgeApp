package com.example.myfridge.data

import com.squareup.moshi.Json

data class User(
    @Json(name= "userId") var userId : String
)
