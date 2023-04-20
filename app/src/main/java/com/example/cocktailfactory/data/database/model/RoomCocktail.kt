package com.example.cocktailfactory.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktail")
data class RoomCocktail(
    @PrimaryKey
    val id: String,
    val name: String,
    val category: String,
    val alcoholic: String,
    val image: String,
    val ingredients: ArrayList<String>,
    val instructions: String
)
