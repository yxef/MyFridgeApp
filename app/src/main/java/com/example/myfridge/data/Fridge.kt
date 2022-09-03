package com.example.myfridge.data

import com.squareup.moshi.Json

data class Fridge(
    @Json(name = "fridgeId") val id: Int,
    @Json(name = "fridgeName") val name: String
)