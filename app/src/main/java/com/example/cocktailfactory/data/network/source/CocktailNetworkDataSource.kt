package com.example.cocktailfactory.data.network.source

import com.example.cocktailfactory.data.network.api.CocktailManagerApi
import com.example.cocktailfactory.data.network.util.apiCall

class CocktailNetworkDataSource(
    private val cocktailManagerApi: CocktailManagerApi
) {
    suspend fun getCocktail() = apiCall {
        cocktailManagerApi.getCocktails().first()
    }
}
