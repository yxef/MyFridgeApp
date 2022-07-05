package com.example.myfridge.data

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey


@Entity(
    tableName = "owners",
    foreignKeys = [ForeignKey(
        entity = Fridge::class,
        parentColumns = ["id"],
        childColumns = ["fridgeId"],
        onDelete = CASCADE
    ), ForeignKey(
        entity = Users::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = CASCADE
    )
    ]
)
data class Owners(
    @PrimaryKey val userId: Int,
    @PrimaryKey val fridgeId: Int,
    @NonNull val isFridgeOwner: Boolean
)