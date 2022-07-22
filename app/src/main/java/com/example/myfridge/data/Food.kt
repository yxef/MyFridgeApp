package com.example.myfridge.data

data class Food (
    val foodId : Int,
    val fridgeId : Int,
    val foodName : String,
    val expiration_date : String,
    val iconId : Int
)