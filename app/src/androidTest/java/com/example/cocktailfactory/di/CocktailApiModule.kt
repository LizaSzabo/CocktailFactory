package com.example.cocktailfactory.di

import com.example.cocktailfactory.data.network.api.CocktailManagerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CocktailApiModule {

    @Singleton
    @Provides
    fun provideCocktailApi(retrofit: Retrofit): CocktailManagerApi {
        return retrofit.create(CocktailManagerApi::class.java)
    }
}
