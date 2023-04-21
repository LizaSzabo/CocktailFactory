package com.example.cocktailfactory.data.network.api

import androidx.room.Delete
import com.example.cocktailfactory.data.network.model.CocktailRequest
import com.example.cocktailfactory.data.network.model.GetCocktailsResponse
import retrofit2.http.GET
import retrofit2.http.PUT

interface CocktailManagerApi {
    @GET("random.php")
    suspend fun getCocktails(): GetCocktailsResponse

    @PUT
    suspend fun saveCocktail(cocktailRequest: CocktailRequest)

    @Delete
    suspend fun deleteCocktail(id: String)
}
