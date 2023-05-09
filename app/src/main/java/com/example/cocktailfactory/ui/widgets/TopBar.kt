package com.example.cocktailfactory.ui.widgets

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cocktailfactory.R

@Composable
fun TopBar(titleText: String, navController: NavController) {
    TopAppBar(
        title = {
            Text(text = "$titleText details", fontSize = 20.sp)
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back button")
            }
        },
        backgroundColor = colorResource(id = R.color.latte),
        contentColor = Color.DarkGray
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar("Cocktail Name", rememberNavController())
}
