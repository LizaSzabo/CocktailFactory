package com.example.cocktailfactory.ui.cocktailslist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cocktailfactory.domain.model.CocktailPresentationModel
import com.example.cocktailfactory.ui.cocktailslist.CocktailsListUiState.CocktailsListReady
import com.example.cocktailfactory.ui.cocktailslist.CocktailsListUiState.Error
import com.example.cocktailfactory.ui.cocktailslist.CocktailsListUiState.Loading
import com.example.cocktailfactory.ui.widgets.CocktailsList
import com.example.cocktailfactory.ui.widgets.SearchView

@Composable
fun CocktailsListScreen(navController: NavController, viewModel: CocktailsListViewModel = hiltViewModel()) {
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
            CocktailsListScreenContent(navController)
        }
        is Error -> {
            CocktailsListErrorContent()
        }
    }
}

@Composable
fun CocktailsListScreenContent(navController: NavController) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Column {
        SearchView(textState)
        CocktailsList(
            navController,
            arrayListOf(CocktailPresentationModel("178335", "Mocha-Berry", "category", "Alcoholic", "image", mutableListOf(), "instructions"))
        )
    }
}

@Composable
fun CocktailsListErrorContent() {
    Text(text = "Cocktails list error")
}
