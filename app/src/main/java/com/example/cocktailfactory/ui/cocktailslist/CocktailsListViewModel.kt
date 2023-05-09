package com.example.cocktailfactory.ui.cocktailslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailfactory.domain.interactors.CocktailInteractor
import com.example.cocktailfactory.domain.model.CocktailPresentationModel
import com.example.cocktailfactory.ui.cocktailslist.CocktailsListUiState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailsListViewModel @Inject constructor(
    private val cocktailInteractor: CocktailInteractor
) : ViewModel() {

    private val _uiState = MutableStateFlow<CocktailsListUiState>(Loading)
    val uiState: StateFlow<CocktailsListUiState> = _uiState.asStateFlow()

    fun getCocktails() {
        viewModelScope.launch(Dispatchers.IO) {
            cocktailInteractor.getCocktails()
            val mockList = arrayListOf(CocktailPresentationModel("id", "name", "category", "Alcoholic", "image", mutableListOf(), "instructions"))
            _uiState.emit(CocktailsListUiState.CocktailsListReady(mockList))
        }
    }
}
