package com.example.cocktailfactory.ui.cocktailslist

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
import com.example.cocktailfactory.ui.cocktailslist.CocktailsListUiState.CocktailsListReady
import com.example.cocktailfactory.ui.cocktailslist.CocktailsListUiState.Error
import com.example.cocktailfactory.ui.cocktailslist.CocktailsListUiState.Loading

@Composable
fun CocktailsListScreen(viewModel: CocktailsListViewModel = hiltViewModel()) {
    val uiState: CocktailsListUiState by viewModel.uiState.collectAsState(Loading)

    when (uiState) {
        is Loading -> {
            viewModel.getCocktails()

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier)
            }
        }
        is CocktailsListReady -> {
            CocktailsListScreenContent()
        }
        is Error -> {
            CocktailsListErrorContent()
        }
    }
}

@Composable
fun CocktailsListScreenContent() {
    Text(text = "Cocktails list")
}

@Composable
fun CocktailsListErrorContent() {
    Text(text = "Cocktails list error")
}
