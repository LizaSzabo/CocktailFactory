package com.example.cocktailfactory.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cocktailfactory.data.database.dao.CocktailDao
import com.example.cocktailfactory.data.database.model.RoomCocktail
import com.example.cocktailfactory.data.database.source.CocktailDataSource
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class CocktailDatabaseDataSourceTest {
    private lateinit var cocktailDao: CocktailDao
    private lateinit var db: CocktailDatabase
    private lateinit var cocktailDataSource: CocktailDataSource

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            CocktailDatabase::class.java
        ).build()
        cocktailDao = db.cocktailDao()
        cocktailDataSource = CocktailDataSource(cocktailDao = cocktailDao)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun readCocktails() {
        val cocktail = RoomCocktail(
            id = "1000",
            name = "Aperol spritz",
            category = "category",
            alcoholic = "Alcoholic",
            image = "image",
            ingredients = arrayListOf(),
            "instructions"
        )
        cocktailDataSource.deleteAllCocktailsFromDb()
        cocktailDataSource.saveCocktailToDb(cocktail)
        val cocktailsFromDb = cocktailDataSource.getAllCocktailsFromDb()
        assertEquals(cocktailsFromDb.size, 1)
    }

    @Test
    @Throws(Exception::class)
    fun writeCocktailAndReadIt() {
        val cocktail = RoomCocktail(
            id = "1111",
            name = "Mocha-Berry",
            category = "category",
            alcoholic = "Alcoholic",
            image = "image",
            ingredients = arrayListOf(),
            "instructions"
        )
        cocktailDataSource.saveCocktailToDb(cocktail)
        val savedCocktail = cocktailDataSource.getCocktailFromDb(cocktailId = "1111")
        assertThat(savedCocktail, equalTo(cocktail))
    }

    @Test
    @Throws(Exception::class)
    fun writeCocktailsAndReadAll() {
        val cocktails = listOf(
            RoomCocktail(
                id = "1111",
                name = "Mocha-Berry",
                category = "category",
                alcoholic = "Alcoholic",
                image = "image",
                ingredients = arrayListOf(),
                "instructions"
            ),
            RoomCocktail(
                id = "2222",
                name = "Hugo",
                category = "category",
                alcoholic = "Alcoholic",
                image = "image",
                ingredients = arrayListOf(),
                "instructions"
            )
        )
        cocktailDataSource.saveCocktailsList(cocktails)
        val savedCocktails = cocktailDataSource.getAllCocktailsFromDb()
        assertContentEquals(savedCocktails, cocktails)
    }

    @Test
    @Throws(Exception::class)
    fun writeCocktailAndDeleteIt() {
        val cocktail = RoomCocktail(
            id = "1111",
            name = "Mocha-Berry",
            category = "category",
            alcoholic = "Alcoholic",
            image = "image",
            ingredients = arrayListOf(),
            "instructions"
        )
        cocktailDataSource.saveCocktailToDb(cocktail)
        cocktailDataSource.deleteCocktailFromDb(cocktail.id)
        val cocktailFromDb = cocktailDataSource.getCocktailFromDb(cocktailId = "1111")
        assertThat(cocktailFromDb, equalTo(null))
    }

    @Test
    @Throws(Exception::class)
    fun writeCocktailsAndDeleteAllIt() {
        val cocktails = listOf(
            RoomCocktail(
                id = "1111",
                name = "Mocha-Berry",
                category = "category",
                alcoholic = "Alcoholic",
                image = "image",
                ingredients = arrayListOf(),
                "instructions"
            ),
            RoomCocktail(
                id = "2222",
                name = "Hugo",
                category = "category",
                alcoholic = "Alcoholic",
                image = "image",
                ingredients = arrayListOf(),
                "instructions"
            )
        )
        cocktailDataSource.saveCocktailsList(cocktails)
        cocktailDataSource.deleteAllCocktailsFromDb()
        val allCocktailsFromDb = cocktailDataSource.getAllCocktailsFromDb()
        assertEquals(allCocktailsFromDb.size, 0)
    }
}
