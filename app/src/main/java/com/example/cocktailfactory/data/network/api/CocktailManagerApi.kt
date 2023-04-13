package com.example.cocktailfactory.data.network.api

import com.example.cocktailfactory.data.network.model.CocktailResponse
import retrofit2.http.GET

interface CocktailManagerApi {
    @GET("random.php")
    suspend fun getCocktails(): List<CocktailResponse>
}
