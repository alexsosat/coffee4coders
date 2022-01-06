package com.example.coffee4coders.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.toSize

@Composable
fun DropDownTextField(
    suggestions: List<String>,
    value: String,
    placeholder: String,
    onChangeValue: (String) -> Unit,
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    val icon = Icons.Filled.ArrowDropDown

    Column {
        CustomTextField(
            value = value,
            placeholder = placeholder,
            onValueChange = onChangeValue,
            enabled = false,
            trailingIcon = {
                Icon(icon,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        isExpanded = !isExpanded
                    }
                )
            },
            onGloballyPositioned = {
                coordinates -> textFieldSize = coordinates.size.toSize()
            }
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier.width(with(LocalDensity.current) {
                textFieldSize.width.toDp()
            }),
            ) {
            suggestions.forEach {
                label -> DropdownMenuItem(onClick = {
                    onChangeValue(label)
            }) {
                Text(text = label, style = MaterialTheme.typography.body2)
            }
            }
        }
    }

}

@Preview(
    showBackground = true
)
@Composable
fun DropDownTextFieldPreview() {
    val cities = listOf(
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

    DropDownTextField(suggestions = cities,
        value = "",
        placeholder = "Ciudades",
        onChangeValue = {}
    )
}