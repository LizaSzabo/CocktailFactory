package com.example.cocktailfactory.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.cocktailfactory.R
import com.example.cocktailfactory.domain.model.CocktailPresentationModel

@Composable
fun CocktailListItemView(cocktail: CocktailPresentationModel, onItemClick: (String) -> Unit) {
    Card(border = BorderStroke(1.dp, colorResource(id = R.color.latte))) {
        Row(
            modifier = Modifier
                .clickable(onClick = { onItemClick(cocktail.id) })
                .background(colorResource(id = R.color.orange))
                .fillMaxWidth()
                .padding(PaddingValues(12.dp, 16.dp))
        ) {
            Column(modifier = Modifier.weight(7f)) {
                Text(text = cocktail.name, fontSize = 24.sp, color = colorResource(id = R.color.latte_text))
                Text(text = cocktail.category, fontSize = 18.sp, color = colorResource(id = R.color.latte_text))
                Text(text = cocktail.alcoholic, fontSize = 18.sp, color = colorResource(id = R.color.latte_text))
            }
            Column(modifier = Modifier.weight(3f).align(Alignment.CenterVertically)) {
                Image(
                    painter = rememberAsyncImagePainter(cocktail.image),
                    contentDescription = stringResource(id = R.string.cocktail_image_description),
                    modifier = Modifier
                        .size(96.dp)
                        .align(Alignment.End)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CocktailListItemViewPreview() {
    CocktailListItemView(
        cocktail = CocktailPresentationModel(
            "111",
            "Cocktail Name",
            "Category",
            "Alcoholic",
            "",
            mutableListOf(),
            "instructions"
        ),
        onItemClick = { }
    )
}
