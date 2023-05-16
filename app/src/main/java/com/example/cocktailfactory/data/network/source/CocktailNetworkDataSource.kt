package com.example.cocktailfactory.data.network.source

import android.util.Log
import com.example.cocktailfactory.data.network.api.CocktailManagerApi
import com.example.cocktailfactory.data.network.model.CocktailRequest
import com.example.cocktailfactory.data.network.model.CocktailResponse
import com.example.cocktailfactory.data.network.util.apiCall

class CocktailNetworkDataSource(
    private val cocktailManagerApi: CocktailManagerApi
) {
    suspend fun getCocktails() = apiCall {
        val listOfRandomCocktails = mutableListOf<CocktailResponse>()
        repeat(5) {
            val newRandomCocktail = cocktailManagerApi.getCocktails().drinks.first()
            listOfRandomCocktails.add(newRandomCocktail)
        }
        Log.i("cocktails: ", listOfRandomCocktails.toString())
        listOfRandomCocktails
    }

    suspend fun saveCocktail(cocktailRequest: CocktailRequest) = apiCall {
        cocktailManagerApi.saveCocktail(cocktailRequest)
    }

    suspend fun deleteCocktail(id: String) = apiCall {
        cocktailManagerApi.deleteCocktail(id)
    }

    suspend fun updateCocktail(cocktailRequest: CocktailRequest) = apiCall {
        cocktailManagerApi.updateCocktail(cocktailRequest)
    }
}
