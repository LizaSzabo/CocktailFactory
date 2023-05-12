package com.example.cocktailfactory.data.network.api

import com.example.cocktailfactory.data.network.api.mock.FakeCocktailApi
import com.example.cocktailfactory.data.network.model.CocktailResponse
import com.example.cocktailfactory.data.network.model.GetCocktailsResponse
import com.example.cocktailfactory.data.network.source.CocktailNetworkDataSource
import com.example.cocktailfactory.data.network.util.NetworkResult
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.net.SocketTimeoutException
import kotlin.test.assertFailsWith

class CocktailNetworkDataSourceTest {

    private val gson: Gson = GsonBuilder().create()

    private var fakeCocktailApi = FakeCocktailApi(gson)

    private val cocktailNetworkDataSource = CocktailNetworkDataSource(fakeCocktailApi)

    @Before
    fun setUp() {
        fakeCocktailApi.isNetworkAvailable = true
        fakeCocktailApi.isSuccess = true
    }

    @Test
    fun getCocktailQueryWorks() = runBlocking {
        val response = cocktailNetworkDataSource.getCocktails()
        assertTrue(response is NetworkResult)
    }

    @Test
    fun getCocktailQueryNoInternetError() = runBlocking {
        fakeCocktailApi.isNetworkAvailable = false

        val exception = assertFailsWith(
            exceptionClass = SocketTimeoutException::class,
            block = { cocktailNetworkDataSource.getCocktails() }
        )
    }

    @Test
    fun getCocktailQuery() = runBlocking {
        val response = cocktailNetworkDataSource.getCocktails()
        val result = (response as NetworkResult).result

        assertTrue(result.toString() == cocktailNetworkResponse.drinks.toString())
    }

    companion object {
        private val cocktailResponse = CocktailResponse(
            idDrink = "12732",
            strDrink = "Chocolate Beverage",
            strCategory = "Cocoa",
            strAlcoholic = "Non alcoholic",
            strInstructions = "Boil milk in the top of a deep double boiler five minutes. Remove from fire and add chocolate, " +
                "mixed with the cinnamon, a little at a time, beating with molinillo or egg beater after each addition. When the chocolate is thoroughly blended, heat to the boiling point. Place over bottom of double boiler and add eggs, whipping constantly, until they are thoroughly blended and the mixture is frothing. Serve in coffee mug. Serves eight.",
            strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/jbqrhv1487603186.jpg",
            strIngredient1 = "Milk",
            strIngredient2 = "Chocolate",
            strIngredient3 = "Cinnamon",
            strIngredient4 = "Egg",
            strIngredient5 = "null",
            strIngredient6 = "null",
            strIngredient7 = "null",
            strIngredient8 = "null",
            strIngredient9 = "null",
            strIngredient10 = "null",
            strIngredient11 = "null",
            strIngredient12 = "null",
            strIngredient13 = "null",
            strIngredient14 = "null",
            strIngredient15 = "null",
            strMeasure1 = "6 cups ",
            strMeasure2 = "3 oz Mexican ",
            strMeasure3 = "1 tsp powdered ",
            strMeasure4 = "3 ",
            strMeasure5 = "null",
            strMeasure6 = "null",
            strMeasure7 = "null",
            strMeasure8 = "null",
            strMeasure9 = "null",
            strMeasure10 = "null",
            strMeasure11 = "null",
            strMeasure12 = "null",
            strMeasure13 = "null",
            strMeasure14 = "null",
            strMeasure15 = "null"
        )
        private val cocktailNetworkResponse = GetCocktailsResponse(
            drinks = arrayListOf(cocktailResponse, cocktailResponse, cocktailResponse, cocktailResponse, cocktailResponse)
        )
    }
}
