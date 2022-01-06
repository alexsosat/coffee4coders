package com.example.coffee4coders.ui.components


import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.coffee4coders.ui.theme.Coffee4codersTheme

typealias NavigationAction = () -> Unit

@Composable
fun CoffeeAppBar(
    title: String? = null,
    navigationIcon: ImageVector? = null,
    navigationAction: NavigationAction? = null,
) {
    val titleText = title ?: "Coffee 4 Coders"
    if (navigationIcon != null && navigationAction != null) {

        TopAppBar(
            title = {
                Text(titleText)
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        navigationAction()
                    },
                ) {
                    Icon(
                        navigationIcon,
                        ""
                    )
                }
            },
            backgroundColor = MaterialTheme.colors.primary
        )
    } else {
        TopAppBar(
            title = {
                Text(titleText)
            },
            backgroundColor = MaterialTheme.colors.primary
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun CoffeAppBarPreview() {
    Coffee4codersTheme {
        CoffeeAppBar(
            "Coffee 4 Coders"
        )
    }
}