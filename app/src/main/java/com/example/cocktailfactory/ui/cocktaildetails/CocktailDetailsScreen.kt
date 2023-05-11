package com.example.cocktailfactory.ui.cocktaildetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cocktailfactory.R
import com.example.cocktailfactory.domain.model.CocktailPresentationModel
import com.example.cocktailfactory.ui.cocktaildetails.CocktailDetailsUiState.CocktailDataReady
import com.example.cocktailfactory.ui.cocktaildetails.CocktailDetailsUiState.CocktailEditing
import com.example.cocktailfactory.ui.cocktaildetails.CocktailDetailsUiState.Error
import com.example.cocktailfactory.ui.cocktaildetails.CocktailDetailsUiState.Loading
import com.example.cocktailfactory.ui.widgets.TopBar

@Composable
fun CocktailDetailsScreen(cocktailId: String, navController: NavController, viewModel: CocktailDetailsViewModel = hiltViewModel()) {
    val uiState: CocktailDetailsUiState by viewModel.uiState.collectAsState(Loading)

    when (uiState) {
        is Loading -> {
            viewModel.getCocktailDetails(cocktailId)

            Column {
                TopBar("Cocktail", navController)
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier,
                        color = colorResource(id = R.color.dark_latte)
                    )
                }
            }
        }

        is CocktailDataReady -> {
            val cocktail = (uiState as CocktailDataReady).cocktail
            Column {
                TopBar(cocktail.name, navController)
                CocktailDetailsScreenContent(
                    cocktail,
                    viewModel::deleteCocktail,
                    viewModel::editCocktail,
                    viewModel::ingredientsListToString
                )
            }
        }

        is CocktailEditing -> {
            val cocktail = (uiState as CocktailEditing).cocktail
            Column {
                TopBar(cocktail.name, navController)
                CocktailDetailsEditingContent(
                    cocktail,
                    viewModel::cancelUpdate,
                    viewModel::updateCocktail,
                    viewModel::ingredientsListToString,
                    viewModel::ingredientsStringToList
                )
            }
        }

        is Error -> {
            Column {
                TopBar("", navController)
                CocktailDetailsErrorContent((uiState as Error).errorMessage)
            }
        }
    }
}

@Composable
fun CocktailDetailsScreenContent(
    cocktail: CocktailPresentationModel,
    deleteCocktail: (String) -> Unit,
    editCocktail: () -> Unit,
    ingredientsListToString: (List<String?>) -> String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.latte))
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = R.drawable.placeholder_cocktail),
            contentDescription = stringResource(id = R.string.cocktail_image_description),
            modifier = Modifier
                .size(280.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = cocktail.name,
            color = Color.DarkGray,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(16.dp, 8.dp, 8.dp, 4.dp),
            textAlign = TextAlign.Start,
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "Category: " + cocktail.category,
            color = Color.DarkGray,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Start,
            fontSize = 22.sp
        )
        Text(
            text = cocktail.alcoholic,
            color = Color.DarkGray,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Start,
            fontSize = 22.sp
        )
        Text(
            text = "Ingredients:",
            color = Color.DarkGray,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            textAlign = TextAlign.Start,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = ingredientsListToString(cocktail.ingredients),
            color = Color.DarkGray,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Start,
            fontSize = 22.sp
        )
        Text(
            text = "Instructions:",
            color = Color.DarkGray,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Start,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = cocktail.instructions,
            color = Color.DarkGray,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Start,
            fontSize = 22.sp
        )
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp)
        ) {
            Button(
                onClick = { deleteCocktail(cocktail.id) },
                modifier = Modifier
                    .padding(12.dp)
                    .width(120.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.dark_latte)),
                border = BorderStroke(2.dp, Color.Red),
                shape = RoundedCornerShape(16)
            ) {
                Text(text = "DELETE", modifier = Modifier.padding(4.dp), fontSize = 18.sp)
            }
            Button(
                onClick = { editCocktail() },
                modifier = Modifier
                    .padding(12.dp)
                    .width(120.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.dark_latte)),
                border = BorderStroke(2.dp, colorResource(id = R.color.border)),
                shape = RoundedCornerShape(16)
            ) {
                Text(text = "EDIT", modifier = Modifier.padding(4.dp), fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun CocktailDetailsEditingContent(
    cocktail: CocktailPresentationModel,
    cancelUpdate: () -> Unit,
    updateCocktail: (CocktailPresentationModel) -> Unit,
    ingredientsListToString: (List<String?>) -> String,
    ingredientsStringToList: (String) -> MutableList<String?>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.latte))
            .verticalScroll(rememberScrollState())
    ) {
        var categoryText by rememberSaveable { mutableStateOf(cocktail.category) }
        var alcoholicText by rememberSaveable { mutableStateOf(cocktail.alcoholic) }
        var ingredientsText by rememberSaveable { mutableStateOf(ingredientsListToString(cocktail.ingredients)) }
        var instructionsText by rememberSaveable { mutableStateOf(cocktail.instructions) }

        Image(
            painter = painterResource(id = R.drawable.placeholder_cocktail),
            contentDescription = stringResource(id = R.string.cocktail_image_description),
            modifier = Modifier
                .size(280.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = cocktail.name,
            color = Color.DarkGray,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(16.dp, 8.dp, 8.dp, 4.dp),
            textAlign = TextAlign.Start,
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Category: ",
                color = Color.DarkGray,
                textAlign = TextAlign.Start,
                fontSize = 22.sp
            )
            BasicTextField(
                value = categoryText,
                onValueChange = {
                    categoryText = it
                },
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 22.sp
                )
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 16.dp)
        ) {
            BasicTextField(
                value = alcoholicText,
                onValueChange = {
                    alcoholicText = it
                },
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 22.sp
                )
            )
        }
        Text(
            text = "Ingredients:",
            color = Color.DarkGray,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            textAlign = TextAlign.Start,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold
        )

        Row(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 16.dp)
        ) {
            BasicTextField(
                value = ingredientsText,
                onValueChange = {
                    ingredientsText = it
                },
                maxLines = 15,
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 22.sp
                )
            )
        }
        Text(
            text = "Instructions:",
            color = Color.DarkGray,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Start,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold
        )

        BasicTextField(
            value = instructionsText,
            onValueChange = {
                instructionsText = it
            },
            singleLine = false,
            textStyle = LocalTextStyle.current.copy(
                fontSize = 22.sp
            ),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 16.dp)
        )
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp)
        ) {
            Button(
                onClick = { cancelUpdate() },
                modifier = Modifier
                    .padding(12.dp)
                    .width(120.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.dark_latte)),
                border = BorderStroke(2.dp, Color.Red),
                shape = RoundedCornerShape(16)
            ) {
                Text(text = "CANCEL", modifier = Modifier.padding(4.dp), fontSize = 18.sp)
            }
            Button(
                onClick = {
                    updateCocktail(
                        CocktailPresentationModel(
                            cocktail.id,
                            cocktail.name,
                            categoryText,
                            alcoholicText,
                            cocktail.image,
                            ingredientsStringToList(ingredientsText),
                            instructionsText
                        )
                    )
                },
                modifier = Modifier
                    .padding(12.dp)
                    .width(120.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.dark_latte)),
                border = BorderStroke(2.dp, Color.Blue),
                shape = RoundedCornerShape(16)
            ) {
                Text(text = "UPDATE", modifier = Modifier.padding(4.dp), fontSize = 18.sp)
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
