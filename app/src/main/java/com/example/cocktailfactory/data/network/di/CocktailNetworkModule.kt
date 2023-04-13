package com.example.cocktailfactory.data.network.di

import com.example.cocktailfactory.data.network.api.CocktailManagerApi
import com.example.cocktailfactory.data.network.source.CocktailNetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CocktailNetworkModule {

    @Singleton
    @Provides
    fun provideCocktailNetworkDataSource(cocktailManagerApi: CocktailManagerApi): CocktailNetworkDataSource =
        CocktailNetworkDataSource(cocktailManagerApi)
}
