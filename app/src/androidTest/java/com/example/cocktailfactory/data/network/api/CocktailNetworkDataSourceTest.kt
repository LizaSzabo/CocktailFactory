package com.example.cocktailfactory.data.network.api

import com.example.cocktailfactory.data.network.api.mock.FakeCocktailApi
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
}
