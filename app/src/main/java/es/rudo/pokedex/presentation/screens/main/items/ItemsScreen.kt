package es.rudo.pokedex.presentation.screens.main.items

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import es.rudo.domain.model.Language
import es.rudo.pokedex.presentation.theme.PokedexTheme

@Composable
fun ItemsScreen(
    viewModel: ItemsViewModel = hiltViewModel()
) {
    viewModel.getItems()
    LazyColumn {
        items(items = viewModel.itemsList) { item ->
            val itemName: String = item.name.firstOrNull { name ->
                name.first == Language.ENGLISH.label
            }?.second ?: "Unknown"
            Text(
                text = itemName,
                color = Color.Black
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ItemsScreenPreview() {
    PokedexTheme {

    }
}
