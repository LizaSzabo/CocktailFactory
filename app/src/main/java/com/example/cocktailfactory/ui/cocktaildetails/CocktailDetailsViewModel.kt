package com.example.cocktailfactory.ui.cocktaildetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailfactory.domain.interactors.CocktailInteractor
import com.example.cocktailfactory.domain.model.CocktailPresentationModel
import com.example.cocktailfactory.ui.cocktaildetails.CocktailDetailsUiState.CocktailDataReady
import com.example.cocktailfactory.ui.cocktaildetails.CocktailDetailsUiState.CocktailEditing
import com.example.cocktailfactory.ui.cocktaildetails.CocktailDetailsUiState.Error
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

    fun getCocktailDetails(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val cocktailWithDetails = cocktailInteractor.getCocktailDetails(id)
            if (cocktailWithDetails == null) {
                _uiState.emit(Error("Cocktail not found!"))
            } else {
                _uiState.emit(CocktailDataReady(cocktailWithDetails))
            }
        }
    }

    fun deleteCocktail(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val successful = cocktailInteractor.deleteCocktail(id)
            if (!successful) {
                _uiState.emit(Error("Could not delete cocktail!"))
            } else {
                // TODO: Success event, navigate back to list
            }
        }
    }

    fun updateCocktail(updatedCocktail: CocktailPresentationModel) {
        viewModelScope.launch(Dispatchers.IO) {
            cocktailInteractor.updateCocktail(updatedCocktail)
            _uiState.emit(CocktailDataReady(updatedCocktail))
        }
    }

    fun cancelUpdate() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentState = _uiState.value as CocktailEditing
            _uiState.emit(CocktailDataReady(currentState.cocktail))
        }
    }

    fun editCocktail() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentState = _uiState.value as CocktailDataReady
            _uiState.emit(CocktailEditing(currentState.cocktail))
        }
    }
}
