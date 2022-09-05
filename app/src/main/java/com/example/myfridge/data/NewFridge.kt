package com.example.myfridge.data

import com.squareup.moshi.Json

data class NewFridge(
    @Json(name = "userId") var userId: String,
    @Json(name = "fridgeName") var fridgeName: String
)