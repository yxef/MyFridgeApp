package com.example.myfridge.network

import com.example.myfridge.data.Food
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "http://82.49.50.77:8080"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MyFridgeApiService {
/*
    @GET("/user/{userId}/Fridges")
    fun getFridgesOfUser() : String

    @GET()
    fun getAccessOfFridge() : String*/

    @GET("/fridge/{fridgeId}/foods")
    suspend fun getFoodOfFridge(@Path("fridgeId") fridgeId : Int ) : List<Food>

}

object MyFridgeApi{

    val retrofitService : MyFridgeApiService by lazy {
        retrofit.create(MyFridgeApiService::class.java)
    }
}