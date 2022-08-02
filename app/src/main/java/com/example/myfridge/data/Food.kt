package com.example.myfridge.data

import com.squareup.moshi.Json

data class Food (
    val foodId : Int,
    val fridgeId : Int,
    val foodName : String,
    @Json(name =  "expiration_date") val expirationDate : String,
    val iconId : Int
)