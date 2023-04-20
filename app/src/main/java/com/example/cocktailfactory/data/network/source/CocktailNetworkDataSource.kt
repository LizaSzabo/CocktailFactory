package com.example.cocktailfactory.data.network.source

import android.util.Log
import com.example.cocktailfactory.data.network.api.CocktailManagerApi
import com.example.cocktailfactory.data.network.util.apiCall

class CocktailNetworkDataSource(
    private val cocktailManagerApi: CocktailManagerApi
) {
    suspend fun getCocktail() = apiCall {
        Log.i("cocktail: ", cocktailManagerApi.getCocktails().drinks.first().toString())
        cocktailManagerApi.getCocktails().drinks.first()
    }
}
