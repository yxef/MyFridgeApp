package com.example.myfridge.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myfridge.data.Food
import com.example.myfridge.data.Fridge
import com.example.myfridge.data.Owners
import com.example.myfridge.data.Users
import com.example.myfridge.data.dao.FoodDao

@Database(
    entities = [
        Food::class, Fridge::class, Users::class, Owners::class],
    version = 2,
    exportSchema = false
)
abstract class MyFridgeDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao

    companion object {
        @Volatile
        private var INSTANCE: MyFridgeDatabase? = null

        fun getDatabase(context: Context): MyFridgeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyFridgeDatabase::class.java,
                    "myfridge_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}