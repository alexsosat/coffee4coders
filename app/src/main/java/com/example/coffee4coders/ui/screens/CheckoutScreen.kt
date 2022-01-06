package com.example.coffee4coders.ui.screens

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun CheckoutScreen(
    navController: NavController,
    product: Product
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    val openDialog = remember { mutableStateOf(false)  }

    Scaffold(
        topBar = {
            CoffeeAppBar(navigationIcon = Icons.Filled.ArrowBack) {
                navController.popBackStack()
            }
        },
    ) {
        Box(contentAlignment = Alignment.BottomCenter) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                ProductCard(
                   product = product
                ) {}

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    CustomTextField(
                        value = name,
                        placeholder = "Nombre Completo",
                        onValueChange = { name = it }
                    )
                    CustomTextField(
                        value = email,
                        placeholder = "Correo Electronico",
                        onValueChange = { email = it }
                    )
                    CustomTextField(
                        value = phone,
                        placeholder = "Telefono Celular",
                        onValueChange = { phone = it }
                    )
                    CustomTextField(
                        value = address,
                        placeholder = "Direccion",
                        onValueChange = { address = it }
                    )
                    DropDownTextField(
                        suggestions = MockDataProvider.listOfCities(),
                        value = city,
                        placeholder = "Ciudad",
                        onChangeValue = { city = it }
                    )

                    Spacer(modifier = Modifier.height(120.dp))
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row() {
                        Text(text = "Subtotal", style = MaterialTheme.typography.caption)
                        Text(
                            text = "$${product.price} ${product.currency}", style = MaterialTheme.typography.body2,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End
                        )
                    }
                    Row {
                        Text(text = "Envío", style = MaterialTheme.typography.caption)
                        Text(
                            text = "10.00 USD", style = MaterialTheme.typography.body2,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "${product.price + 10.00} USD", style = MaterialTheme.typography.h5,
                            textAlign = TextAlign.Start
                        )
                        CustomButton(label = "confirmar compra") {
                            openDialog.value = true
                        }
                    }
                }

            }
        }

        if (openDialog.value) {

            AlertDialog(
                onDismissRequest = {
                    // Dismiss the dialog when the user clicks outside the dialog or on the back
                    // button. If you want to disable that functionality, simply use an empty
                    // onCloseRequest.
                    openDialog.value = false
                },
                title = {
                    Text(text = "Compra realizada con éxito", style = MaterialTheme.typography.h5)
                },
                text = {
                    Text("Disfruta de tu café :D", style = MaterialTheme.typography.body2)
                },
                confirmButton = {
                    Button(

                        onClick = {
                            openDialog.value = false
                        }) {
                        Text("YAY!")
                    }
                },

            )
        }
    }
    }


@Preview(
    showBackground = true
)
@Composable
fun CheckoutScreenPreview() {
    val product = MockDataProvider.getProductById(1) ?: Product(id = 0,name = "", summary = "", description = "", price = 0.00, countryISO = "", currency = "")
    val navController = rememberNavController()
    Coffee4codersTheme() {
        CheckoutScreen(
            navController = navController,
            product = product
        )
    }
}