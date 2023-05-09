package com.example.cocktailfactory.domain.model

import com.example.cocktailfactory.data.database.model.RoomCocktail
import com.example.cocktailfactory.data.network.model.CocktailResponse

data class CocktailPresentationModel(
    val id: String,
    val name: String,
    val category: String,
    val alcoholic: String,
    val image: String,
    val ingredients: MutableList<String>,
    val instructions: String
)

fun CocktailResponse.toCocktailPresentationModel() = CocktailPresentationModel(
    id = idDrink,
    name = strDrink,
    category = strCategory,
    alcoholic = strAlcoholic,
    image = strDrinkThumb,
    ingredients = getConcatenatedListOfIngredients(this),
    instructions = strInstructions
)

private fun getConcatenatedListOfIngredients(cocktail: CocktailResponse): MutableList<String> {
    val listOfIngredients = mutableListOf<String>()
    listOfIngredients.add(cocktail.strIngredient1)
    listOfIngredients.add(cocktail.strIngredient2)
    listOfIngredients.add(cocktail.strIngredient3)
    listOfIngredients.add(cocktail.strIngredient4)
    listOfIngredients.add(cocktail.strIngredient5)
    listOfIngredients.add(cocktail.strIngredient6)
    listOfIngredients.add(cocktail.strIngredient7)
    listOfIngredients.add(cocktail.strIngredient8)
    listOfIngredients.add(cocktail.strIngredient9)
    listOfIngredients.add(cocktail.strIngredient10)
    listOfIngredients.add(cocktail.strIngredient11)
    listOfIngredients.add(cocktail.strIngredient12)
    listOfIngredients.add(cocktail.strIngredient13)
    listOfIngredients.add(cocktail.strIngredient14)
    listOfIngredients.add(cocktail.strIngredient15)
    return listOfIngredients
}

fun CocktailPresentationModel.toCocktailRoomModel() = RoomCocktail(
    id = id,
    name = name,
    category = category,
    alcoholic = alcoholic,
    image = image,
    ingredients = ingredients as ArrayList<String>,
    instructions = instructions
)

fun RoomCocktail.toPresentationModelCocktail() = CocktailPresentationModel(
    id = id,
    name = name,
    category = category,
    alcoholic = alcoholic,
    image = image,
    ingredients = ingredients,
    instructions = instructions
)
