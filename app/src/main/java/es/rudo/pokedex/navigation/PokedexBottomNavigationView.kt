package es.rudo.pokedex.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rudo.pokedex.presentation.theme.PokedexTheme
import kotlinx.coroutines.selects.select

@Composable
fun PokedexBottomNavigationView() {

    val navigationItems = listOf(
        NavigationItem.Berries,
        NavigationItem.Pokemon,
        NavigationItem.Items.apply {
            selected = true
        }
    )

    BottomNavigation(
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        navigationItems.forEach { item ->
            var selected by remember { mutableStateOf(item.selected) }
            BottomNavigationItem(
                icon = {
                    Image(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(text = item.title) },
                alwaysShowLabel = true,
                selected = selected,
                onClick = {
                    selected = !selected
                },
                modifier = Modifier.background(
                    if (selected) Color.Red
                    else Color.Transparent
                )
            )
        }
    }
}

@Preview
@Composable
fun PokedexBottomNavigationViewPreview() {
    PokedexTheme {
        PokedexBottomNavigationView()
    }
}