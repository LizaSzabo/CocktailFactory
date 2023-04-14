package com.example.cocktailfactory.domain.model

data class CocktailPresentationModel(
    val id: String,
    val name: String,
    val category: String,
    val alcoholic: String,
    val image: String,
    val ingredients: MutableList<String>,
    val instructions: String
)
