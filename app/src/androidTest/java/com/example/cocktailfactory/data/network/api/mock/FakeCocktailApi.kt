package com.example.cocktailfactory.data.network.api.mock

import com.example.cocktailfactory.data.network.api.CocktailManagerApi
import com.example.cocktailfactory.data.network.api.utils.getHttpException
import com.example.cocktailfactory.data.network.api.utils.getResponse
import com.example.cocktailfactory.data.network.model.CocktailRequest
import com.example.cocktailfactory.data.network.model.CocktailResponse
import com.example.cocktailfactory.data.network.model.GetCocktailsResponse
import com.google.gson.Gson
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeCocktailApi @Inject constructor(private val gson: Gson) : CocktailManagerApi {

    var isNetworkAvailable = true
    var isSuccess = true
    override suspend fun getCocktails(): GetCocktailsResponse = fakeCall {
        when (isSuccess) {
            true -> "fake/get_Cocktail_success.json".getResponse(gson)
            false -> throw "fake/get_Cocktail_fail.json".getHttpException(gson)
        }
    }

    override suspend fun saveCocktail(cocktailRequest: CocktailRequest): CocktailResponse = fakeCall {
        when (isSuccess) {
            true -> "fake/post_Cocktail_success.json".getResponse(gson)
            false -> throw "fake/post_Cocktail_fail.json".getHttpException(gson)
        }
    }

    override suspend fun deleteCocktail(id: String): String = fakeCall {
        when (isSuccess) {
            true -> "fake/delete_Cocktail_success.json".getResponse(gson)
            false -> throw "fake/delete_Cocktail_fail.json".getHttpException(gson)
        }
    }
}

private suspend fun <T : Any> FakeCocktailApi.fakeCall(block: suspend () -> T): T {
    if (isNetworkAvailable.not()) {
        throw SocketTimeoutException()
    }
    return block()
}
