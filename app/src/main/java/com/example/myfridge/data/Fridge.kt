package com.example.myfridge.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fridges")
data class Fridge(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @NonNull @ColumnInfo val name : String
)