package com.example.cocktailfactory.ui.cocktailslist

import com.example.cocktailfactory.domain.model.CocktailPresentationModel

sealed class CocktailsListUiState {
    object Loading : CocktailsListUiState()
    class CocktailsListReady(val cocktails: List<CocktailPresentationModel>) : CocktailsListUiState()
    class Error(val errorMessage: String) : CocktailsListUiState()
}
