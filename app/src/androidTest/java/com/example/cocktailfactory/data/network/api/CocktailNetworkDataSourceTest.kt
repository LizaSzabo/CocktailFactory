package com.example.cocktailfactory.data.network.api

import com.example.cocktailfactory.data.network.api.mock.FakeCocktailApi
import com.example.cocktailfactory.data.network.source.CocktailNetworkDataSource
import com.example.cocktailfactory.data.network.util.NetworkResult
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class CocktailNetworkDataSourceTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fakeCocktailApi: FakeCocktailApi

    private val cocktailNetworkDataSource = CocktailNetworkDataSource(fakeCocktailApi)

    @Before
    fun setUp() {
        hiltRule.inject()
        fakeCocktailApi.isNetworkAvailable = true
        fakeCocktailApi.isSuccess = true
    }

    @Test
    fun getCocktailQueryWorks() = runBlocking {
        val actualResponse = cocktailNetworkDataSource.getCocktails()

        assertTrue(actualResponse is NetworkResult)
    }
}
