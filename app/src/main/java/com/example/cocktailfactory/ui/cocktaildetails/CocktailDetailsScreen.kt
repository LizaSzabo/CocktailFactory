package com.example.cocktailfactory.ui.cocktaildetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cocktailfactory.ui.cocktaildetails.CocktailDetailsUiState.CocktailDataReady
import com.example.cocktailfactory.ui.cocktaildetails.CocktailDetailsUiState.Error
import com.example.cocktailfactory.ui.cocktaildetails.CocktailDetailsUiState.Loading

@Composable
fun CocktailDetailsScreen(viewModel: CocktailDetailsViewModel = hiltViewModel()) {
    val uiState: CocktailDetailsUiState by viewModel.uiState.collectAsState(Loading)

    when (uiState) {
        is Loading -> {
            viewModel.getCocktailDetails()

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier)
            }
        }
        is CocktailDataReady -> {
            CocktailDetailsScreenContent()
        }
        is Error -> {
            CocktailDetailsErrorContent()
        }
    }
}

@Composable
fun CocktailDetailsScreenContent() {
    Text(text = "Cocktail details")
}

@Composable
fun CocktailDetailsErrorContent() {
    Text(text = "Cocktail details error")
}
