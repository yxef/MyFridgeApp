package com.example.myfridge.network

import com.example.myfridge.data.Food
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val IP_ADDRESS = "10.0.2.2"
private const val PORT = "8080"
private const val BASE_URL = "http://${IP_ADDRESS}:${PORT}"

// logger
private val logging : HttpLoggingInterceptor = HttpLoggingInterceptor()
    .setLevel(HttpLoggingInterceptor.Level.BODY)

// client
private val client : OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(logging)
    .build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()



interface MyFridgeApiService {
/*
    @GET("/user/{userId}/Fridges")
    fun getFridgesOfUser() : String

    @GET()
    fun getAccessOfFridge() : String*/

    @GET("/fridge/{fridgeId}/foods")
    suspend fun getFoodOfFridge(@Path("fridgeId") fridgeId : Int ) : List<Food>

    @Headers("Content-Type: application/json")
    @POST("/fridge/add/food")
    suspend fun addFoodToFridge(@Body foodToInsert: Food)

}

object MyFridgeApi{

    val retrofitService : MyFridgeApiService by lazy {
        retrofit.create(MyFridgeApiService::class.java)
    }
}