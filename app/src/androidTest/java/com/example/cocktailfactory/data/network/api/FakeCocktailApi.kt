package com.example.cocktailfactory.data.network.api

import com.example.cocktailfactory.data.network.model.CocktailRequest
import com.example.cocktailfactory.data.network.model.GetCocktailsResponse
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeCocktailApi @Inject constructor(private val gson: Gson) : CocktailManagerApi {
    override suspend fun getCocktails(): GetCocktailsResponse {
        TODO("Not yet implemented")
    }

    override suspend fun saveCocktail(cocktailRequest: CocktailRequest) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCocktail(id: String) {
        TODO("Not yet implemented")
    }
}
