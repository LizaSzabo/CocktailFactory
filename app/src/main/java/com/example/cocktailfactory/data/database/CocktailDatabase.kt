package com.example.cocktailfactory.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cocktailfactory.data.database.dao.CocktailDao
import com.example.cocktailfactory.data.database.model.RoomCocktail

@Database(
    exportSchema = false,
    version = 1,
    entities = [RoomCocktail::class]
)
abstract class CocktailDatabase : RoomDatabase() {

    abstract fun cocktailDao(): CocktailDao
}
