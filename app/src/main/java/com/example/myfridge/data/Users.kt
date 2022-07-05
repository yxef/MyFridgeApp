package com.example.myfridge.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Users (
    @PrimaryKey val id : Int,
    @ColumnInfo val name : String = "" // Empty default value
)