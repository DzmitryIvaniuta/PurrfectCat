package com.example.purrfectcat.core.data.storage.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats")
data class CatEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val url: String
)
