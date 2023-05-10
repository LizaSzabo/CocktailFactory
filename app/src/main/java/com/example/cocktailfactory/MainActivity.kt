package com.example.cocktailfactory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cocktailfactory.ui.cocktaildetails.CocktailDetailsScreen
import com.example.cocktailfactory.ui.cocktailslist.CocktailsListScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = Color.Black,
                    darkIcons = false
                )
            }
            Scaffold(
                backgroundColor = colorResource(id = R.color.background_latte)
            ) { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            CocktailsListScreen(navController = navController)
        }
        composable(
            "details/{cocktailName}",
            arguments = listOf(navArgument("cocktailName") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("cocktailName")?.let { cocktailName ->
                CocktailDetailsScreen(cocktailId = cocktailName, navController = navController)
            }
        }
    }
}
