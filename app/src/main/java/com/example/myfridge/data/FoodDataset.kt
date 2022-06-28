package com.example.myfridge.data

import android.util.Log
import com.example.myfridge.model.Food

class FoodDataset {

    private val dataset = mutableListOf(
        Food("Paprika", "22.2.1998", 0),
        Food("Banane", "23.12.2032", 0),
        Food("Lampone", "24.4.2022", 0),
        Food("Libri", "25.3.2045", 0),
        Food("Colla", "26.9.2012", 0),
        Food("Sperma", "27.10.2005", 0),
        Food("Poggers", "28.1.2002", 0),
    )

    fun loadTestFoodItems() : MutableList<Food>{
        return dataset
    }

    fun addFoodItem(nome : String, data : String, iconId : Int){
        dataset.add(Food(nome, data, iconId))
        val test : String = dataset.toString()
        Log.d("Dataset Debug", "{$test}" )
    }
}