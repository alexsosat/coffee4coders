package com.example.coffee4coders.ui.screens

import android.icu.text.CaseMap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.coffee4coders.models.Product
import com.example.coffee4coders.ui.components.*
import com.example.coffee4coders.ui.theme.Coffee4codersTheme
import com.example.coffee4coders.utilities.MockDataProvider

@Composable
fun DetailsScreen(
    navController: NavController,
    product: Product
) {
    val countryISO = CountryISO.valueOf(product.countryISO)

    Scaffold(
        topBar = {
            CoffeeAppBar(
                navigationIcon = Icons.Filled.ArrowBack,
                navigationAction = { navController.popBackStack() }
            )
        },
        content = {
            Column(
                modifier = Modifier.verticalScroll(
                    rememberScrollState()
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                ) {
                    Image(
                        painter = painterResource(id = countryISO.getBackgroundImage()),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    TitleText(title = product.name)
                    Text(
                        text = product.summary,
                        style = MaterialTheme.typography.caption
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    BodyText(body = product.description)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "$ ${product.price} ${product.currency}",
                            style = MaterialTheme.typography.h5,
                            textAlign = TextAlign.End
                        )
                        CustomButton(label = "Continuar") {
                            navController.navigate("checkout/${product.id}"){
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
    showBackground = true,
)
@Composable
fun DetailsScreenPreview() {
    val navController = rememberNavController()
    val product = MockDataProvider.getProductById(1) ?: Product(id = 0,name = "", summary = "", description = "", price = 0.00, countryISO = "", currency = "")
    Coffee4codersTheme() {
        DetailsScreen(
            navController = navController,
            product = product
        )
    }

}