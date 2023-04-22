package com.example.cocktailfactory.di

import com.example.cocktailfactory.data.network.api.CocktailManagerApi
import com.example.cocktailfactory.data.network.api.mock.FakeCocktailApi
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CocktailApiModule::class]
)
class TestApiModule {

    @Provides
    @Singleton
    fun provideCocktailApi(fakeCocktailApi: FakeCocktailApi): CocktailManagerApi {
        return fakeCocktailApi
    }
}
