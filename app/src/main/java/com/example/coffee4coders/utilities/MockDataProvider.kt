package com.example.coffee4coders.utilities

import com.example.coffee4coders.models.Product

class MockDataProvider {
    companion object {
        fun listOfProducts(): List<Product>{
            return listOf(
                Product(
                    id = 0,
                    name = "Café de Colombia",
                    summary = "Un café bien rico traido de las tierras de Colombia",
                    description = "Este café es bien nutritivo creeme ;)",
                    price = 35.00,
                    currency = "USD",
                    countryISO = "COL"
                ),
                Product(
                    id = 1,
                    name = "Café de Costa Rica",
                    summary = "Un café bien rico traido de las tierras de Costa Rica",
                    description = "Este café es bien nutritivo creeme ;)",
                    price = 15.00,
                    currency = "USD",
                    countryISO = "CRI"
                ),
                Product(
                    id = 2,
                    name = "Café de Nicaragua",
                    summary = "Un café bien rico traido de las tierras de Nicaragua",
                    description = "Este café es bien nutritivo creeme ;)",
                    price = 90.00,
                    currency = "USD",
                    countryISO = "NIC"
                ),
                Product(
                    id = 3,
                    name = "Café de Brasil",
                    summary = "Un café bien rico traido de las tierras de Brasil",
                    description = "Este café es bien nutritivo creeme ;)",
                    price = 28.00,
                    currency = "USD",
                    countryISO = "BRA"
                ),
            )
        }

        fun getProductById(id: Int): Product? {
            val list = MockDataProvider.listOfProducts()
            return list.find { it.id == id }
        }

        fun listOfCities(): List<String>{
            return listOf(
                "Mexico City, Mexico",
                "The Habana, Cuba",
                "Cancun, Mexico",
                "Medellin, Colombia",
                "Buenos Aires, Argentina",
                "Sao Paulo, Brasil",
                "Lima, Peru",
                "Montevideo, Uruguay",
                "Panama City, Panama"
            )
        }
    }
}