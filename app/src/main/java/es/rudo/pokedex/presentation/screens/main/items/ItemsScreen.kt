package es.rudo.pokedex.presentation.screens.main.items

import android.content.Context
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import es.rudo.domain.model.Item
import es.rudo.domain.model.Language
import es.rudo.pokedex.R
import es.rudo.pokedex.UiState
import es.rudo.pokedex.presentation.components.ErrorPopUp
import es.rudo.pokedex.presentation.components.PageButtons
import es.rudo.pokedex.presentation.components.ProgressLoader

@Composable
fun ItemsScreen(
    context: Context,
    viewModel: ItemsViewModel = hiltViewModel()
) {
    Crossfade(targetState = viewModel.uiState) {
        when (it.value) {
            is UiState.Loading -> ProgressLoader()
            is UiState.Error -> {
                ErrorPopUp(
                    context.getString(R.string.network_error)
                )
            }
            is UiState.ShowContent -> ItemsContent(viewModel)
        }
    }
}

@Composable
fun ItemsContent(
    viewModel: ItemsViewModel
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        ItemsGrid(items = viewModel.itemsList)
        PageButtons(
            onClickPrevious = { viewModel.previousPage() },
            onClickNext = { viewModel.nextPage() }
        )
    }
}

@Composable
fun ItemsGrid(
    items: List<Item>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        items(items = items, key = {it.id}) { item ->
            ItemCard(item = item)
        }
    }
}

@Composable
fun ItemCard(item: Item) {
    val itemName: String = item.name.firstOrNull { name ->
        name.first == Language.ENGLISH.label
    }?.second ?: "Unknown"
    Text(
        text = itemName,
        color = Color.Black
    )
    Spacer(modifier = Modifier.height(5.dp))
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ItemsScreenPreview() {
    val items: ArrayList<Item> = ArrayList()
    for (i in 0..9) {
        items.add(
            Item(
                id = i,
                name = arrayListOf(
                    Pair("en", "Master Ball")
                ),
                0,
                ""
            )
        )
    }
    ItemsGrid(
        items = items
    )
}
