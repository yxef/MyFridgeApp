package com.example.myfridge.data

import com.squareup.moshi.Json

data class Food(
    @Json(name = "foodId") val foodId: Int?,
    @Json(name = "fridgeId") val fridgeId: Int,
    @Json(name = "foodName") val foodName: String,
    @Json(name = "expiration_date") val expirationDate: String,
    @Json(name = "iconId") val iconId: Int
)