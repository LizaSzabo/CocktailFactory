package com.example.cocktailfactory.ui.cocktaildetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cocktailfactory.R
import com.example.cocktailfactory.domain.model.CocktailPresentationModel
import com.example.cocktailfactory.ui.cocktaildetails.CocktailDetailsUiState.CocktailDataReady
import com.example.cocktailfactory.ui.cocktaildetails.CocktailDetailsUiState.Error
import com.example.cocktailfactory.ui.cocktaildetails.CocktailDetailsUiState.Loading
import com.example.cocktailfactory.ui.cocktaildetails.CocktailDetailsUiState.CocktailEditing

@Composable
fun CocktailDetailsScreen(cocktailId: String, viewModel: CocktailDetailsViewModel = hiltViewModel()) {
    val uiState: CocktailDetailsUiState by viewModel.uiState.collectAsState(Loading)

    when (uiState) {
        is Loading -> {
            viewModel.getCocktailDetails(cocktailId)

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier)
            }
        }
        is CocktailDataReady -> {
            CocktailDetailsScreenContent(
                (uiState as CocktailDataReady).cocktail,
                viewModel::deleteCocktail,
                viewModel::editCocktail,
            )
        }
        is CocktailEditing -> CocktailDetailsEditingContent(
            (uiState as CocktailEditing).cocktail,
            viewModel::cancelUpdate,
            viewModel::updateCocktail,
        )
        is Error -> {
            CocktailDetailsErrorContent((uiState as Error).errorMessage)
        }
    }
}

@Composable
fun CocktailDetailsScreenContent(cocktail: CocktailPresentationModel, deleteCocktail: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.latte))
            .wrapContentSize(Alignment.TopStart)
    ) {
        Image(
            painter = painterResource(id = R.drawable.placeholder_cocktail),
            contentDescription = stringResource(id = R.string.cocktail_image_description)
        )
        Text(
            text = cocktail.name,
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Start,
            fontSize = 22.sp
        )
        Text(
            text = cocktail.category,
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Start,
            fontSize = 22.sp
        )
        Text(
            text = cocktail.alcoholic,
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Start,
            fontSize = 22.sp
        )
        Text(
            text = "Ingredients:",
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Start,
            fontSize = 22.sp
        )
        Text(
            text = "Instructions:",
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Start,
            fontSize = 22.sp
        )
        Text(
            text = cocktail.instructions,
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Start,
            fontSize = 22.sp
        )
        Row {
            Button(
                onClick = { deleteCocktail(cocktail.id) },
                modifier = Modifier.padding(12.dp)
            ) {
                Text(text = "Delete", modifier = Modifier.padding(4.dp))
            }
        }
    }
}


@Composable
fun CocktailDetailsEditingContent(
    cocktail: CocktailPresentationModel,
    cancelUpdate: () -> Unit,
    updateCocktail: (CocktailPresentationModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.latte))
            .wrapContentSize(Alignment.TopStart)
    ) {
        Image(
            painter = painterResource(id = R.drawable.placeholder_cocktail),
            contentDescription = stringResource(id = R.string.cocktail_image_description)
        )
        Text(
            text = cocktail.name,
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Start,
            fontSize = 22.sp
        )
        Text(
            text = cocktail.category,
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Start,
            fontSize = 22.sp
        )
        Text(
            text = cocktail.alcoholic,
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Start,
            fontSize = 22.sp
        )
        Text(
            text = "Ingredients:",
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Start,
            fontSize = 22.sp
        )
        Text(
            text = "Instructions:",
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Start,
            fontSize = 22.sp
        )
        Text(
            text = cocktail.instructions,
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Start,
            fontSize = 22.sp
        )
        Row {
            Button(
                onClick = { cancelUpdate },
                modifier = Modifier.padding(12.dp)
            ) {
                Text(text = "Cancel", modifier = Modifier.padding(4.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    // CocktailDetailsScreenContent(CocktailPresentationModel("id", "name", "category", "Alcoholic", "image", mutableListOf(), "instructions"))
}

@Composable
fun CocktailDetailsErrorContent(message: String) {
    Text(text = message)
}
