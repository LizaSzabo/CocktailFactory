package com.example.cocktailfactory.data.database.di

import android.content.Context
import androidx.room.Room
import com.example.cocktailfactory.data.database.CocktailDatabase
import com.example.cocktailfactory.data.database.dao.CocktailDao
import com.example.cocktailfactory.data.database.source.CocktailDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CocktailDatabaseModule {
    @Provides
    @Singleton
    fun provideCocktailDatabase(@ApplicationContext context: Context): CocktailDatabase {
        return Room.databaseBuilder(context, CocktailDatabase::class.java, "cocktailsDatabase")
            .build()
    }

    @Provides
    @Singleton
    fun provideCocktailDao(cocktailDatabase: CocktailDatabase): CocktailDao {
        return cocktailDatabase.cocktailDao()
    }

    @Provides
    @Singleton
    fun provideCocktailDataSource(cocktailDao: CocktailDao): CocktailDataSource =
        CocktailDataSource(cocktailDao = cocktailDao)
}
