package com.example.cocktailfactory.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cocktailfactory.data.database.dao.CocktailDao
import com.example.cocktailfactory.data.database.model.RoomCocktail
import com.example.cocktailfactory.data.database.typeconverter.ArrayListConverter

@Database(
    exportSchema = false,
    version = 1,
    entities = [RoomCocktail::class]
)
@TypeConverters(ArrayListConverter::class)
abstract class CocktailDatabase : RoomDatabase() {

    abstract fun cocktailDao(): CocktailDao
}
