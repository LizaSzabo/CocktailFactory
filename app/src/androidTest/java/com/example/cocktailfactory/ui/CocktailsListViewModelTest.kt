package com.example.cocktailfactory.ui

import androidx.lifecycle.ViewModel
import com.example.cocktailfactory.data.network.api.mock.FakeCocktailApi
import com.example.cocktailfactory.domain.interactors.CocktailInteractor
import com.example.cocktailfactory.ui.cocktailslist.CocktailsListViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

class CocktailsListViewModelTest : ViewModel() {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fakeCocktailApi: FakeCocktailApi

    @Inject
    lateinit var cocktailInteractor: CocktailInteractor

    private lateinit var cocktailsListViewModel: CocktailsListViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        cocktailsListViewModel = CocktailsListViewModel(cocktailInteractor)
        fakeCocktailApi.isSuccess = true
        fakeCocktailApi.isNetworkAvailable = true
    }

    @After
    fun tearDown() {
    }
}
