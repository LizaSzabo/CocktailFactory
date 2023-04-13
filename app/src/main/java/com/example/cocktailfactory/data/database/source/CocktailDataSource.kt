package com.example.cocktailfactory.data.database.source

import com.example.cocktailfactory.data.database.dao.CocktailDao
import com.example.cocktailfactory.data.database.model.RoomCocktail

class CocktailDataSource(
    private val cocktailDao: CocktailDao
) {
    fun saveCocktailToDb(roomCocktail: RoomCocktail) {
        cocktailDao.saveCocktail(roomCocktail)
    }

    fun saveCocktailsList(roomCocktailsList: List<RoomCocktail>) {
        cocktailDao.saveCocktails(roomCocktailsList)
    }

    fun deleteCocktailFromDb(roomCocktail: RoomCocktail) {
        cocktailDao.deleteCocktail(roomCocktail.id)
    }

    fun deleteAllCocktailsFromDb() {
        cocktailDao.deleteAllCocktails()
    }

    fun getCocktailFromDb(cocktailId: String): RoomCocktail {
        return cocktailDao.getCocktail(cocktailId)
    }

    fun getAllCocktailsFromDb(): List<RoomCocktail> {
        return cocktailDao.getAllCocktails()
    }
}
