package com.example.inorganicwastecraftingapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Favorite(
    @PrimaryKey(autoGenerate = false)

    @field:SerializedName("judul")
    val judul: String,

    @field:SerializedName("link")
    val link: String,
)