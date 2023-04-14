package com.example.cocktailfactory.ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cocktailfactory.domain.model.CocktailPresentationModel

@Composable
fun CocktailsList(cocktails: ArrayList<CocktailPresentationModel>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(cocktails) { cocktail ->
            CocktailListItemView(
                cocktailName = cocktail.name,
                onItemClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountryListPreview() {
    CocktailsList(arrayListOf(CocktailPresentationModel("id", "name", "category", true, "image", mutableListOf(), "instructions")))
}
