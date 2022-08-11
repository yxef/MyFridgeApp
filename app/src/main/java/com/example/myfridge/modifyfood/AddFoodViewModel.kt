package com.example.myfridge.modifyfood

import androidx.lifecycle.ViewModel
import com.example.myfridge.R


class AddFoodViewModel : ViewModel(){
    private val _iconDrawableId = listOf(
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

    val iconList = _iconDrawableId



}