package com.example.cocktailfactory.data.network.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CocktailModule {

    @Singleton
    @Provides
    fun provideCocktailApi(retrofit: Retrofit): CocktailManagerApi {
        return retrofit.create(CocktailManagerApi::class.java)
    }
}
