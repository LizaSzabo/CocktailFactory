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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cocktailfactory.R
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
                CircularProgressIndicator(
                    modifier = Modifier,
                    color = colorResource(id = R.color.dark_latte)
                )
            }
        }

        is CocktailsListReady -> {
            CocktailsListScreenContent(navController, (uiState as CocktailsListReady).cocktails)
        }

        is Error -> {
            CocktailsListErrorContent((uiState as Error).errorMessage)
        }
    }
}

@Composable
fun CocktailsListScreenContent(navController: NavController, cocktailsList: List<CocktailPresentationModel>) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Column {
        SearchView(textState)
        CocktailsList(
            navController,
            cocktailsList.filter { cocktail ->
                cocktail.name.uppercase().contains(textState.value.text.uppercase())
            }
        )
    }
}

@Composable
fun CocktailsListErrorContent(errorMessage: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = errorMessage,
            fontSize = 24.sp,
            color = Color.Red,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
