package com.example.myfridge.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = ""
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface MyFridgeApiService {

    @GET("/user/{userId}/Fridges")
    fun getFridgesOfUser() : String

    @GET()
    fun getAccessOfFridge() : String

    @GET()
    fun getFoodOfFridge() : String

}