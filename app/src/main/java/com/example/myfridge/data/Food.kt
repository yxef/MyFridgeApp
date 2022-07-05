package com.example.myfridge.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

// TODO("add fridgeId foreign key")

@Entity(
    tableName = "foods",
    foreignKeys = [ForeignKey(
        entity = Fridge::class,
        parentColumns = ["id"],
        childColumns = ["fridgeId"],
        onDelete = CASCADE
    )]
)
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo val fridgeId: Int,
    @NonNull @ColumnInfo val name: String,
    @NonNull @ColumnInfo val expiration_date: String,
    @NonNull val iconId: Int = 0
)