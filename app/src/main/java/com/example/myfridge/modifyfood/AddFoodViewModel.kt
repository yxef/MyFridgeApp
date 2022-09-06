package com.example.myfridge.modifyfood

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfridge.R
import com.example.myfridge.data.Food
import com.example.myfridge.network.MyFridgeApi
import kotlinx.coroutines.launch


class AddFoodViewModel : ViewModel() {
    val iconList = listOf(
        R.drawable.ic_baby_food,
        R.drawable.ic_canned_food,
        R.drawable.ic_chicken_food,
        R.drawable.ic_crab_food,
        R.drawable.ic_fish_food,
        R.drawable.ic_fruit_orange_food,
        R.drawable.ic_ham_food,
        R.drawable.ic_soda_food,
        R.drawable.ic_vegetables_pumpkin_food,
        R.drawable.ic_wine_food
    )

    var foodToInsert: Food? = null

    var selectedIconPosition : Int = 1


    fun createFood(
        fridgeId: Int,
        foodName: String,
        expirationDate: String,
        iconId: Int
    ) {
        foodToInsert = Food(
            foodId = null,
            fridgeId = fridgeId,
            foodName = foodName,
            expirationDate = expirationDate,
            iconId = iconId
        )
    }

    fun pushFoodToDatabase() {
        if (foodToInsert != null) {
            viewModelScope.launch {
                try {
                    MyFridgeApi.retrofitService.addFoodToFridge(foodToInsert!!)
                } catch (e: Exception) {
                    Log.d("Dataset", "Failure: ${e.message}")
                }
            }
        }
    }


}