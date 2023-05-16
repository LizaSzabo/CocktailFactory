package com.example.cocktailfactory.ui.cocktaildetails

import com.example.cocktailfactory.domain.model.CocktailPresentationModel

sealed class CocktailDetailsUiState {
    object Loading : CocktailDetailsUiState()
    data class CocktailDataReady(val cocktail: CocktailPresentationModel) : CocktailDetailsUiState()
    data class CocktailEditing(val cocktail: CocktailPresentationModel) : CocktailDetailsUiState()
    object DeleteSuccessful : CocktailDetailsUiState()
    data class Error(val errorMessage: String) : CocktailDetailsUiState()
}
