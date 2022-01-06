package com.example.coffee4coders.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.coffee4coders.ui.components.*
import com.example.coffee4coders.ui.theme.Coffee4codersTheme
import com.example.coffee4coders.utilities.MockDataProvider

@Composable
fun FeedScreen(navController: NavController) {

    val list = MockDataProvider.listOfProducts()

    Scaffold(
        topBar = { CoffeeAppBar(
            title = "Coffee 4 Coders"
        ) },
    content = {

            Surface(
                color = MaterialTheme.colors.background
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            TitleText(title = "Bienvenido a Coffee4Coders")
                            BodyText(body = "Te invitamos a que explores la pagina y veas los productos que tenemos para ofrecer")
                        }
                    }

                    items(list) { product ->
                        ProductCard(
                            product = product
                        )
                        {
                            navController.navigate(route = "details/${product.id}") {
                                launchSingleTop = true
                            }
                        }
                    }

            }
        }
    }
    )





}

@Preview(
    showBackground = true
)
@Composable
fun FeedScreenPreview() {
    val navController = rememberNavController()
    FeedScreen(navController = navController)
}