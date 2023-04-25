package com.example.cocktailfactory.domain.interactors

import android.util.Log
import com.example.cocktailfactory.data.database.source.CocktailDataSource
import com.example.cocktailfactory.data.network.source.CocktailNetworkDataSource
import com.example.cocktailfactory.data.network.util.NetworkError
import com.example.cocktailfactory.data.network.util.NetworkResponse
import com.example.cocktailfactory.data.network.util.NetworkResult
import com.example.cocktailfactory.data.network.util.NetworkUnavailable
import com.example.cocktailfactory.data.network.util.UnknownHostError
import com.example.cocktailfactory.domain.model.CocktailPresentationModel
import com.example.cocktailfactory.domain.model.toCocktailPresentationModel
import com.example.cocktailfactory.domain.model.toCocktailRoomModel
import com.example.cocktailfactory.domain.model.toPresentationModelCocktail
import javax.inject.Inject

class CocktailInteractor @Inject constructor(
    private val cocktailDataSource: CocktailDataSource,
    private val cocktailNetworkDataSource: CocktailNetworkDataSource
) {
    suspend fun getCocktails(): NetworkResponse<List<CocktailPresentationModel>> {
        return when (val getCocktailsResponse = cocktailNetworkDataSource.getCocktails()) {
            is NetworkError -> {
                NetworkError(getCocktailsResponse.errorMessage)
            }
            UnknownHostError -> NetworkError("NoNetworkError")
            is NetworkResult -> {
                val newPresentationModelCocktails = getCocktailsResponse.result.map { it.toCocktailPresentationModel() }
                val newRoomModelCocktails = newPresentationModelCocktails.map { it.toCocktailRoomModel() }
                cocktailDataSource.saveCocktailsList(newRoomModelCocktails)

                val allPresentationModelCocktails = cocktailDataSource.getAllCocktailsFromDb().map { it.toPresentationModelCocktail() }
                Log.i("allCocktails: ", allPresentationModelCocktails.toString())
                NetworkResult(allPresentationModelCocktails)
            }
            NetworkUnavailable -> NetworkError("No internet")
        }
    }

    fun getCocktailDetails(id: String): CocktailPresentationModel? {
        val roomModelCocktail = cocktailDataSource.getCocktailFromDb(id)
        return roomModelCocktail?.toPresentationModelCocktail()
    }

    fun deleteCocktail(id: String): Boolean {
        cocktailDataSource.deleteCocktailFromDb(id)
        val roomModelCocktail = cocktailDataSource.getCocktailFromDb(id)
        return (roomModelCocktail == null)
    }

    fun updateCocktail(updatedCocktail: CocktailPresentationModel) {
        cocktailDataSource.saveCocktailToDb(updatedCocktail.toCocktailRoomModel())
    }
}
