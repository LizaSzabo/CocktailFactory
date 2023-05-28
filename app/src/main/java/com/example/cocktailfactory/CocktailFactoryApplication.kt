package com.example.cocktailfactory

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CocktailFactoryApplication : Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        CocktailFactoryApplication.appContext = applicationContext
    }
}
