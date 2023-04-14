package com.example.cocktailfactory.ui.cocktaildetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailfactory.domain.interactors.CocktailInteractor
import com.example.cocktailfactory.domain.model.CocktailPresentationModel
import com.example.cocktailfactory.ui.cocktaildetails.CocktailDetailsUiState.CocktailDataReady
import com.example.cocktailfactory.ui.cocktaildetails.CocktailDetailsUiState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailDetailsViewModel @Inject constructor(
    private val cocktailInteractor: CocktailInteractor
) : ViewModel() {

    private val _uiState = MutableStateFlow<CocktailDetailsUiState>(Loading)
    val uiState: StateFlow<CocktailDetailsUiState> = _uiState.asStateFlow()

    fun getCocktailDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            val mockCocktail = CocktailPresentationModel("id", "name", "category", "Alcoholic", "image", mutableListOf(), "instructions")
            _uiState.emit(CocktailDataReady(mockCocktail))
        }
    }
}
