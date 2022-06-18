package es.rudo.pokedex.presentation.screens.main.items

import android.content.Context
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import es.rudo.domain.model.Item
import es.rudo.domain.model.Language
import es.rudo.pokedex.App
import es.rudo.pokedex.R
import es.rudo.pokedex.UiState
import es.rudo.pokedex.helpers.extensions.toFormattedPrice
import es.rudo.pokedex.presentation.components.ErrorPopUp
import es.rudo.pokedex.presentation.components.PageButtons
import es.rudo.pokedex.presentation.components.ProgressLoader
import java.util.*
import kotlin.collections.ArrayList

@Composable
fun ItemsScreen(
    viewModel: ItemsViewModel = hiltViewModel()
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (items, buttons) = createRefs()
        ItemsGrid(
            items = viewModel.itemsList,
            modifier = Modifier
                .constrainAs(items) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(buttons.top)
                    height = Dimension.fillToConstraints
                }
        )
        PageButtons(
            isPreviousButtonVisible = viewModel.isPreviousButtonVisible(),
            isNextButtonVisible = viewModel.isNextButtonVisible(),
            page = viewModel.page,
            onClickPrevious = {
                if (viewModel.uiState.value != UiState.Loading)
                    viewModel.previousPage()
            },
            onClickNext = {
                if (viewModel.uiState.value != UiState.Loading)
                    viewModel.nextPage()
            },
            modifier = Modifier
                .padding(16.dp)
                .constrainAs(buttons) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        )

        AnimatedVisibility(
            visible = viewModel.uiState.value
                    is UiState.Loading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            ProgressLoader()
        }

        AnimatedVisibility(
            visible = viewModel.uiState.value
                    is UiState.Error
        ) {
            ErrorPopUp(
                message = stringResource(R.string.network_error)
            ) { viewModel.previousPage() }
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
        contentPadding = PaddingValues(
            start = 16.dp,
            top = 16.dp,
            end = 16.dp,
            bottom = 16.dp,
        ),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        items(items = items, key = { it.id }) { item ->
            ItemCard(item = item)
        }
    }
}

@Composable
fun ItemCard(item: Item) {
    val itemName: String = item.name.firstOrNull { name ->
        name.first == App.preferences.getLanguage()
    }?.second ?: "Unknown"
    val itemCost: String = item.cost.toString().toFormattedPrice()
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 8.dp,
                    vertical = 18.dp
                )
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = item.sprite,
                    error = painterResource(id = R.drawable.ic_error)
                ),
                contentDescription = itemName,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )

            Text(
                text = itemName,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${if (item.cost == 0) "---" else itemCost} ¥",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
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
    val items: ArrayList<Item> = ArrayList()
    for (i in 0..9) {
        items.add(
            Item(
                id = i,
                name = arrayListOf(
                    Pair(Language.SPANISH.tag, "Master Ball")
                ),
                2000,
                ""
            )
        )
    }
    ItemsGrid(
        items = items
    )
}
