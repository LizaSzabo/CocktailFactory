package com.example.cocktailfactory.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cocktailfactory.data.database.model.RoomCocktail

@Dao
interface CocktailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCocktail(cocktail: RoomCocktail)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCocktails(cocktails: Collection<RoomCocktail>)

    @Query("SELECT * FROM cocktail WHERE id = :cocktailId")
    fun getCocktail(cocktailId: String): RoomCocktail?

    @Query("SELECT * FROM cocktail")
    fun getAllCocktails(): List<RoomCocktail>

    @Query("DELETE FROM cocktail")
    fun deleteAllCocktails()

    @Query("DELETE FROM cocktail WHERE id = :cocktailId")
    fun deleteCocktail(cocktailId: String)
}
