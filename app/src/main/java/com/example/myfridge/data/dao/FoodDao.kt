package com.example.myfridge.data.dao

import androidx.room.*
import com.example.myfridge.data.Food

@Dao
interface FoodDao {

    // returns a list of food from a fridge
    @Query("SELECT * FROM foods WHERE fridgeId = :specificFridgeId ORDER BY expiration_date ASC")
    fun getAllFoodOfFridge(specificFridgeId : Int) : List<Food>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(food : Food)

    @Update
    fun update(food: Food)

    @Delete
    fun delete(food: Food)
}