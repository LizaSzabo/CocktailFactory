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

@RunWith(AndroidJUnit4::class)
class CocktailEntityReadWriteTest {
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
}
