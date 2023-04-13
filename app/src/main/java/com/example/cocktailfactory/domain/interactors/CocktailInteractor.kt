package com.example.cocktailfactory.domain.interactors

import com.example.cocktailfactory.data.database.source.CocktailDataSource
import com.example.cocktailfactory.data.network.source.CocktailNetworkDataSource
import javax.inject.Inject

class CocktailInteractor @Inject constructor(
    private val cocktailDataSource: CocktailDataSource,
    private val cocktailNetworkDataSource: CocktailNetworkDataSource
)
