package es.rudo.pokedex.presentation.screens.main.items

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import es.rudo.domain.model.Item
import es.rudo.domain.model.Language

@Composable
fun ItemsScreen(
    viewModel: ItemsViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ItemsGrid(
            items = viewModel.itemsList,
            modifier = Modifier.weight(1f)
        )
        Button(
            onClick = { viewModel.nextPage() },
            modifier = Modifier.weight(0.1f)
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
        ) {
            Text(text = "Next")
        }
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
