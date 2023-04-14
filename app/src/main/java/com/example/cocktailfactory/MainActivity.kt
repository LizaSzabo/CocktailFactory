package com.example.cocktailfactory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.cocktailfactory.ui.cocktailslist.CocktailsListScreen
import com.example.cocktailfactory.ui.widgets.TopBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                topBar = { TopBar() },
                backgroundColor = colorResource(id = R.color.purple_500)
            ) { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    CocktailsListScreen()
                }
            }
        }
    }
}
