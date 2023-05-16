package com.example.cocktailfactory.ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.cocktailfactory.domain.model.CocktailPresentationModel

@Composable
fun CocktailsList(navController: NavController, cocktails: List<CocktailPresentationModel>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(cocktails) { cocktail ->
            CocktailListItemView(
                cocktail = cocktail,
                onItemClick = { selectedCocktail ->
                    navController.navigate("details/$selectedCocktail") {
                        popUpTo("main") {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountryListPreview() {
    // CocktailsList(NavController(Context), arrayListOf(CocktailPresentationModel("id", "name", "category", true, "image", mutableListOf(), "instructions")))
}
