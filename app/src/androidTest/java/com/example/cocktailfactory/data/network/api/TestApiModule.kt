package com.example.cocktailfactory.data.network.api

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CocktailModule::class]
)
class TestApiModule {

    @Provides
    @Singleton
    fun provideCocktailApi(fakeCocktailApi: FakeCocktailApi): CocktailManagerApi {
        return fakeCocktailApi
    }
}
