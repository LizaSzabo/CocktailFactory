package com.example.cocktailfactory.ui.cocktailslist

import com.example.cocktailfactory.domain.model.CocktailPresentationModel

sealed class CocktailsListUiState {
    object Loading : CocktailsListUiState()
    data class CocktailsListReady(val cocktails: List<CocktailPresentationModel>) : CocktailsListUiState()
    data class Error(val errorMessage: String) : CocktailsListUiState()
}
