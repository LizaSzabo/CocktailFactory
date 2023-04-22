package com.example.cocktailfactory.data.network.api

import androidx.room.Delete
import com.example.cocktailfactory.data.network.model.CocktailRequest
import com.example.cocktailfactory.data.network.model.CocktailResponse
import com.example.cocktailfactory.data.network.model.GetCocktailsResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface CocktailManagerApi {
    @GET("random.php")
    suspend fun getCocktails(): GetCocktailsResponse

    @POST
    suspend fun saveCocktail(cocktailRequest: CocktailRequest): CocktailResponse

    @Delete
    suspend fun deleteCocktail(id: String): String
}
