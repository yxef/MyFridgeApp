package com.example.myfridge.data

import com.example.myfridge.model.Food

class TestDataset {

    fun loadTestFoodItems() : List<Food>{
        return listOf<Food>(
            Food("Patate", "27.06.2022", 0),
            Food("Lamponi", "28.06.2022", 0),
            Food("Riso", "30.06.2022", 0),
            Food("Latte", "11.07.2022", 0),
            Food("Fragole", "10.09.2022", 0),
            Food("Carne", "04.10.2022", 0),
            Food("Sperma", "11.06.2023", 0),
            Food("Formaggio", "23.12.2022", 0),
            Food("Colla", "27.06.2029", 0)
        )
    }
}